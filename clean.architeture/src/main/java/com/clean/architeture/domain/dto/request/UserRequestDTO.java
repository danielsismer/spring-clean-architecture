package com.clean.architeture.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request body for creating or updating a user")
public record UserRequestDTO(

        @Schema(description = "User email", example = "ana@email.com")
        @NotBlank(message = "email is required")
        @Email(message = "invalid email")
        String email,

        @Schema(description = "User CPF", example = "123.456.789-01")
        @NotBlank(message = "cpf is required")
        String cpf,

        @Schema(description = "User phone", example = "(11) 98765-4321")
        String phone
) {}