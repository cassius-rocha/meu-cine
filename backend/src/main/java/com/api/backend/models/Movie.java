package com.api.backend.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String originalTitle;

    @Column(nullable = false, length = 2000)
    private String overview;

    @Column(nullable = false)
    private String releaseDate;

    @Column(nullable = false)
    private String director;

    private Integer voteAverage;

    private Integer userVote;

    @ElementCollection
    private List<String> watchProviders = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    private List<Shelf> shelves = new ArrayList<>();

    public Movie() {
    }

    public Movie(Long id, String title, String originalTitle, String overview, String releaseDate,
                 String director, Integer voteAverage, Integer userVote, List<String> watchProviders) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.director = director;
        this.voteAverage = voteAverage;
        this.userVote = userVote;
        this.watchProviders = watchProviders;
    }

    // Getters e setters

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

    public Integer getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Integer voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getUserVote() {
        return userVote;
    }

    public void setUserVote(Integer userVote) {
        this.userVote = userVote;
    }

    public List<String> getwatchProviders() {
        return watchProviders;
    }

    public void setwatchProviders(List<String> watchProviders) {
        this.watchProviders = watchProviders;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }
}
