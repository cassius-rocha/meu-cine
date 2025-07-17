package com.api.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.backend.service.MovieService;

@RestController
@RequestMapping("/api/filmes")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{word}")
    public String searchMovieOrPerson(@PathVariable String word) {
        try {
            return movieService.searchMulti(word);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Erro ao buscar dados do TMDB\"}";
        }
    }

}
