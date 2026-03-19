package com.clean.architeture.domain.dto.filter;

import org.springframework.stereotype.Component;

public record UserFilter (
        String email,
        String cpf
) {
}
