package com.api.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class ShelfDTO {
    private Long id;
    private String name;
    private List<MovieDTO> movies = new ArrayList<>();

    // Construtor padrão
    public ShelfDTO() {
    }

    // Construtor com campos
    public ShelfDTO(Long id, String name, List<MovieDTO> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }
}
