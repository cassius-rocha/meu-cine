package com.api.backend.dto;

import java.util.List;

public class Person {
    private String name;
    private Long tmdbId;
    private List<MovieDTO> movies;

    // Construtor padrão
    public Person() {
    }

    // Construtor com todos os campos
    public Person(String name, Long tmdbId, List<MovieDTO> movies) {
        this.name = name;
        this.tmdbId = tmdbId;
        this.movies = movies;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }
}
