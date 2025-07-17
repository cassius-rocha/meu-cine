package com.api.backend.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponse {

    @JsonProperty("movies")
    private List<MovieDTO> movies;

    @JsonProperty("people")
    private List<Person> people;

    public SearchResponse() {
    }

    public SearchResponse(List<MovieDTO> movies, List<Person> people) {
        this.movies = movies;
        this.people = people;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
