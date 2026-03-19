package com.clean.architeture.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UserRequestDTO (

        @Email
        @NotBlank
        String email,

        @CPF
        @NotBlank
        String cpf,

        @NotBlank
        String phone
) {
}
