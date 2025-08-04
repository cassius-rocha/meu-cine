package com.api.backend.service;

import com.api.backend.dto.MovieDTO;
import com.api.backend.dto.Person;
import com.api.backend.dto.SearchResponse;
import com.api.backend.models.Movie;
import com.api.backend.models.User;
import com.api.backend.models.Shelf;

import com.api.backend.repository.MovieRepository;
import com.api.backend.repository.ShelfRepository;
import com.api.backend.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final ShelfRepository shelfRepository;

    private final UserRepository userRepository;    

    public MovieService(MovieRepository movieRepository, ShelfRepository shelfRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.shelfRepository = shelfRepository;
        this.userRepository = userRepository;
    }

    @Value("${tmdb.api.token}")
    private String tmdbToken;

    @Value("${tmdb.api.base-url}")
    private String tmdbBaseUrl;

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public SearchResponse searchMulti(String query) throws Exception {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String endpoint = tmdbBaseUrl + "/search/multi?query=" + encodedQuery;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Authorization", "Bearer " + tmdbToken)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = mapper.readTree(response.body());
        JsonNode results = root.get("results");

        List<MovieDTO> movies = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        if (results != null && results.isArray()) {
            for (JsonNode result : results) {
                String mediaType = result.get("media_type").asText();

                if (mediaType.equals("movie")) {
                    MovieDTO movie = parseMovieFromNode(result);
                    movies.add(movie);
                } else if (mediaType.equals("person")) {
                    Long personId = result.get("id").asLong();
                    String personName = result.get("name").asText();

                    List<MovieDTO> personMovies = fetchPersonMovieCredits(personId);
                    Person person = new Person(personName, personId, personMovies);
                    people.add(person);
                }
            }
        }

        return new SearchResponse(movies, people);
    }

    private List<MovieDTO> fetchPersonMovieCredits(Long personId) throws Exception {
        String endpoint = tmdbBaseUrl + "/person/" + personId + "/movie_credits";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Authorization", "Bearer " + tmdbToken)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = mapper.readTree(response.body());

        List<MovieDTO> personMovies = new ArrayList<>();
        JsonNode cast = root.get("cast");

        if (cast != null && cast.isArray()) {
            for (JsonNode movie : cast) {
                MovieDTO dto = parseMovieFromNode(movie);
                personMovies.add(dto);
            }
        }

        return personMovies;
    }

    private String getMovieDirector(Long movieId) throws IOException, InterruptedException {
        String endpoint = tmdbBaseUrl + "/movie/" + movieId + "/credits";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Authorization", "Bearer " + tmdbToken)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = mapper.readTree(response.body());

        JsonNode crewArray = root.get("crew");
        if (crewArray != null && crewArray.isArray()) {
            for (JsonNode crewMember : crewArray) {
                String job = crewMember.get("job").asText();
                if ("Director".equalsIgnoreCase(job)) {
                    return crewMember.get("name").asText();
                }
            }
        }

        return "Desconhecido";
    }

    private MovieDTO parseMovieFromNode(JsonNode node) {
        Long id = node.has("id") ? node.get("id").asLong() : null;
        String title = node.has("title") ? node.get("title").asText() : node.path("name").asText();
        String originalTitle = node.has("original_title") ? node.get("original_title").asText() : title;
        String overview = node.has("overview") ? node.get("overview").asText() : "";
        String releaseDate = node.has("release_date") ? node.get("release_date").asText() : "";

        String director = "";
        try {
            director = getMovieDirector(id);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // ou use um logger se preferir
            director = "Desconhecido"; // fallback caso ocorra erro
        }

        String posterPath = node.has("poster_path") ? node.get("poster_path").asText() : "";
        Double voteAverage = node.has("vote_average") ? node.get("vote_average").asDouble() : 0.0;

        return new MovieDTO(id, title, originalTitle, overview, releaseDate, director, posterPath, voteAverage,
                null, new ArrayList<>(), new ArrayList<>());
    }

    public Movie saveMovieToShelf(Movie movie, String shelfName, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String normalizedShelfName = shelfName.trim().toLowerCase();

        Shelf shelf = shelfRepository.findByNameIgnoreCaseAndUser(normalizedShelfName, user)
                .orElseGet(() -> {
                    Shelf newShelf = new Shelf();
                    newShelf.setName(normalizedShelfName);
                    newShelf.setUser(user);
                    return newShelf;
                });

        Movie existingMovie = movieRepository.findById(movie.getId())
                .orElseGet(() -> movieRepository.save(movie));

        if (!shelf.getMovies().contains(existingMovie)) {
            shelf.getMovies().add(existingMovie);
        }

        shelfRepository.save(shelf);

        return existingMovie;
    }

}