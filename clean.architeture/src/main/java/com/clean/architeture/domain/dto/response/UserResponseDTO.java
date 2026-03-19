package com.clean.architeture.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User response data")
public record UserResponseDTO(

        @Schema(description = "User ID", example = "1")
        Long id,

        @Schema(description = "User email", example = "ana@email.com")
        String email,

        @Schema(description = "User CPF", example = "123.456.789-01")
        String cpf,

        @Schema(description = "User phone", example = "(11) 98765-4321")
        String phone
) {}
