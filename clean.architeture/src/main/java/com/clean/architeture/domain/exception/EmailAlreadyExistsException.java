package com.clean.architeture.domain.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(@Email @NotBlank String email){
        super("Email already register!");
    }
}
