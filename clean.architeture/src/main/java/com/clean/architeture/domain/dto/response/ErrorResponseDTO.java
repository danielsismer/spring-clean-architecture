package com.clean.architeture.domain.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDTO (
        LocalDateTime localDateTime,
        int httpStatus,
        String message,
        Map<String, String> errors
){
}
