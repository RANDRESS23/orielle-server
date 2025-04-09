package com.orielle.orielle_server.domain.exception;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException(String message) { super(message); }
}
