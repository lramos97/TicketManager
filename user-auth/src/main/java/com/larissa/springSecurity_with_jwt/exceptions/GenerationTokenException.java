package com.larissa.springSecurity_with_jwt.exceptions;

public class GenerationTokenException extends RuntimeException{

    public GenerationTokenException() {
        super("Error Generating Token.");
    }

    public GenerationTokenException(String message) {
        super(message);
    }
}
