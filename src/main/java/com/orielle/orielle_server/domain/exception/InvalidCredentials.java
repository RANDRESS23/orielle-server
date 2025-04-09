package com.orielle.orielle_server.domain.exception;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials(String message) { super(message); }
}
