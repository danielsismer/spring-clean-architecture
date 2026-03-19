package com.clean.architeture.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DomainException extends RuntimeException{

    private final int statusHttp;

    public DomainException(String message, int statusHttp){
        super(message);
        this.statusHttp = statusHttp;
    }



}
