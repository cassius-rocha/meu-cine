package com.api.backend.dto;

import com.api.backend.models.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
    @NotBlank String login,
    @NotBlank String password,
    @NotBlank String email,
    @NotBlank String name,
    @NotNull UserRole role
) { }
