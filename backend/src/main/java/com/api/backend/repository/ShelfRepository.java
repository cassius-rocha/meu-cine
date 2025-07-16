package com.api.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {

}
