package com.api.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
