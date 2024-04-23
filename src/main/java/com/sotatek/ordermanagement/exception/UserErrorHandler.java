package com.sotatek.ordermanagement.exception;

import com.sotatek.ordermanagement.exception.dto.UserNameExistsErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserErrorHandler {
    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<UserNameExistsErrorResponse> handlePasswordNotMatchedError(PasswordNotMatchedException err) {
        final UserNameExistsErrorResponse response = new UserNameExistsErrorResponse(err.getUsername());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameExistsException.class)
    public ResponseEntity<UserNameExistsException> handleUsernameExistsError(UserNameExistsException err) {
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundException> handleUserNotFoundError(UserNotFoundException err) {
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
