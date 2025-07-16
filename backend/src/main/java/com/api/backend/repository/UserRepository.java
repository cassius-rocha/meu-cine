package com.api.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
