package com.api.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.Shelf;
import com.api.backend.models.User;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {

    Optional<Shelf> findByNameIgnoreCaseAndUser(String name, User user);
}
