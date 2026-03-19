package com.clean.architeture.domain.exception;

public class UserNotFoundException extends DomainException{

        public UserNotFoundException(String name){
            super("User: " + name + " not found! ", 404);
        }

}
