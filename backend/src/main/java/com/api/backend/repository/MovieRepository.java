package com.api.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
