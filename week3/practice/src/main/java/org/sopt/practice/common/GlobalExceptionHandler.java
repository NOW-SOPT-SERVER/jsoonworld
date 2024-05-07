package org.sopt.practice.common;

import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.common.dto.ErrorResponse;
import org.sopt.practice.exception.NotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(HttpStatus.NOT_FOUND.value(), ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException e) {
        ErrorResponse response = ErrorResponse.of(ErrorMessage.DATABASE_ACCESS_ERROR.getStatus(), ErrorMessage.DATABASE_ACCESS_ERROR.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
