package com.sotatek.ordermanagement.exception;


import javax.naming.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<ErrorResponse> handlePasswordNotMatchedError(
            PasswordNotMatchedException ex) {
        ErrorCode error = ErrorCode.PASSWORD_NOT_MATCHED;
        return new ResponseEntity<>(
                new ErrorResponse(error.getErrorCode(), error.getErrorMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameExistsError(UserNameExistsException ex) {
        ErrorCode error = ErrorCode.USERNAME_EXISTS;
        return new ResponseEntity<>(
                new ErrorResponse(error.getErrorCode(), error.getErrorMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundError(NotFoundException ex) {
        ErrorCode error = ErrorCode.NOT_FOUND;
        return new ResponseEntity<>(
                new ErrorResponse(error.getErrorCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationError(AuthenticationException ex) {
        ErrorCode error = ErrorCode.AUTHENTICATION_ERROR;
        return new ResponseEntity<>(
                new ErrorResponse(error.getErrorCode(), error.getErrorMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomerPhoneExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerPhoneExistsError(
            CustomerPhoneExistsException ex) {
        ErrorCode error = ErrorCode.CUSTOMER_PHONE_EXISTS;
        return new ResponseEntity<>(
                new ErrorResponse(error.getErrorCode(), error.getErrorMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNameExistsException.class)
    public ResponseEntity<ErrorResponse> handleProductNameExistsException(
            ProductNameExistsException ex) {
        ErrorCode error = ErrorCode.PRODUCT_NAME_EXISTS;
        return new ResponseEntity<>(
                new ErrorResponse(error.getErrorCode(), error.getErrorMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductQuantityIsNotEnoughException.class)
    public ResponseEntity<ErrorResponse> handleProductQuantityIsNotEnoughException(
            ProductQuantityIsNotEnoughException ex) {
        ErrorCode error = ErrorCode.PRODUCT_NAME_EXISTS;
        return new ResponseEntity<>(
                new ErrorResponse(error.getErrorCode(), error.getErrorMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateStringIsNotCorrectException.class)
    public ResponseEntity<ErrorResponse> handleProductQuantityIsNotEnoughException(
            DateStringIsNotCorrectException ex) {
        ErrorCode error = ErrorCode.DATE_STRING_IS_NOT_CORRECT;
        return new ResponseEntity<>(
                new ErrorResponse(error.getErrorCode(), error.getErrorMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
