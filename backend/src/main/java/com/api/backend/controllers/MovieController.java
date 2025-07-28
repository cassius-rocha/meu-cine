package com.api.backend.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.backend.dto.SearchResponse;
import com.api.backend.models.Movie;
import com.api.backend.models.Shelf;
import com.api.backend.models.User;
import com.api.backend.repository.MovieRepository;
import com.api.backend.repository.ShelfRepository;
import com.api.backend.repository.UserRepository;
import com.api.backend.service.MovieService;

@RestController
@RequestMapping("/api/filmes")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/{shelfName}")
    public ResponseEntity<?> saveOnShelf(@RequestBody Movie movie, @PathVariable String shelfName,
            Principal principal) {
        try {
            Movie savedMovie = movieService.saveMovieToShelf(movie, shelfName, principal.getName());
            return ResponseEntity.ok(savedMovie);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar filme na shelf");
        }
    }

    @GetMapping("/{word}")
    public SearchResponse searchMovieOrPerson(@PathVariable String word) {
        try {
            return movieService.searchMulti(word);
        } catch (Exception e) {
            e.printStackTrace();
            return new SearchResponse();
        }
    }

}
