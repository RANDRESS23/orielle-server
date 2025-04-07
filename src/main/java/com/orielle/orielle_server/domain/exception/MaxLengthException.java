package com.orielle.orielle_server.domain.exception;

public class MaxLengthException extends RuntimeException {
    public MaxLengthException(String message) {
        super(message);
    }
}
