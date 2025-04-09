package com.orielle.orielle_server.configuration.exception_handler;

import com.orielle.orielle_server.domain.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(MaxLengthException.class)
    public ResponseEntity<ExceptionResponse> handleMaxLengthException(MaxLengthException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(AlreadyExistsFieldException.class)
    public ResponseEntity<ExceptionResponse> handleAlreadyExistsFieldException(AlreadyExistsFieldException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.CONFLICT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NegativeNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> handleNegativeNotAllowedException(NegativeNotAllowedException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidSortByParamException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidSortByParamException(InvalidSortByParamException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAuthorizationDeniedException(AuthorizationDeniedException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.FORBIDDEN.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> handleExpiredJwtException(ExpiredJwtException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidFieldException(InvalidFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ExceptionResponse> handleDateTimeParseException(DateTimeParseException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCredentials(InvalidCredentials exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidProductQuantityException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidProductQuantityException(InvalidProductQuantityException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyCartException(EmptyCartException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
            String.format(exception.getMessage()),
            HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
