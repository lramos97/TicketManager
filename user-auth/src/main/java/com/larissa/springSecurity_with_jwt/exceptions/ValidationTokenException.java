package com.larissa.springSecurity_with_jwt.exceptions;

public class ValidationTokenException extends RuntimeException{

    public ValidationTokenException() {
        super("Invalid or Expired Token.");
    }

    public ValidationTokenException(String message) {
        super(message);
    }
}
