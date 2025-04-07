package com.orielle.orielle_server.domain.exception;

public class AlreadyExistsFieldException extends RuntimeException {
    public AlreadyExistsFieldException(String message) {
        super(message);
    }
}
