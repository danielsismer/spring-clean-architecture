package com.clean.architeture.domain.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UserResponseDTO(
        Long id,
        String email,
        String cpf,
        String phone
) {
}
