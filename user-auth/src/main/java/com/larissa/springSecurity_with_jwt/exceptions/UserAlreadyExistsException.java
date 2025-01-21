package com.larissa.springSecurity_with_jwt.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException() {
        super("User Already Exists.");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
