package com.sotatek.ordermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class UserErrorHandler {
    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<ErrorResponse> handlePasswordNotMatchedError(PasswordNotMatchedException ex) {
        ErrorCode error = ErrorCode.PASSWORD_NOT_MATCHED;
        return new ResponseEntity<>(new ErrorResponse(error.getErrorCode(), error.getErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameExistsError(UserNameExistsException ex) {
        ErrorCode error = ErrorCode.USERNAME_EXISTS;
        return new ResponseEntity<>(new ErrorResponse(error.getErrorCode(), error.getErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundError(UserNotFoundException ex) {
        ErrorCode error = ErrorCode.USER_NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(error.getErrorCode(), error.getErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationError(AuthenticationException ex) {
        ErrorCode error = ErrorCode.AUTHENTICATION_ERROR;
        return new ResponseEntity<>(new ErrorResponse(error.getErrorCode(), error.getErrorMessage()), HttpStatus.UNAUTHORIZED);
    }
}
