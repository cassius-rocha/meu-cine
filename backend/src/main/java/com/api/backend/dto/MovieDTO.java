package com.api.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {

    private Long id;
    private String title;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String director;
    private Double voteAverage;
    private Double userVote;
    private List<String> watchProviders = new ArrayList<>();
    private List<String> shelves = new ArrayList<>();

    // Construtor padrão
    public MovieDTO() {
    }

    // Construtor com todos os campos
    public MovieDTO(Long id, String title, String originalTitle, String overview,
                    String releaseDate, String director, Double voteAverage,
                    Double userVote, List<String> watchProviders, List<String> shelves) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.director = director;
        this.voteAverage = voteAverage;
        this.userVote = userVote;
        this.watchProviders = watchProviders;
        this.shelves = shelves;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Double getUserVote() {
        return userVote;
    }

    public void setUserVote(Double userVote) {
        this.userVote = userVote;
    }

    public List<String> getWatchProviders() {
        return watchProviders;
    }

    public void setWatchProviders(List<String> watchProviders) {
        this.watchProviders = watchProviders;
    }

    public List<String> getShelves() {
        return shelves;
    }

    public void setShelves(List<String> shelves) {
        this.shelves = shelves;
    }
}