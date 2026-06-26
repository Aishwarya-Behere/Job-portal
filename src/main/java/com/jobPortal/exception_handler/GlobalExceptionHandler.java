package com.jobPortal.exception_handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.jobPortal.custom_exceptions.InvalidInputException;
import com.jobPortal.custom_exceptions.ResourceNotFoundException;
import com.jobPortal.dtos.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404: company or job not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        System.out.println("in resource not found exc");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(e.getMessage(), "Failed"));
    }

    // 400: business logic violation (e.g. job already UNAVAILABLE)
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<?> handleInvalidInputException(InvalidInputException e) {
        System.out.println("in invalid input exc");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(e.getMessage(), "Failed"));
    }

    // 400: wrong enum value sent in request param (e.g. "IT" instead of "INFORMATION_TECHNOLOGY")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        System.out.println("in type mismatch exc");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse("Invalid value '" + e.getValue()
                        + "' for parameter: " + e.getName(), "Failed"));
    }

    // 400: missing required request param in URL
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParam(MissingServletRequestParameterException e) {
        System.out.println("in missing param exc");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse("Missing required parameter: " + e.getParameterName(), "Failed"));
    }

    // 400: @Valid annotation failures (field-level validation errors in DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
        System.out.println("in @Valid exc");
        List<FieldError> fieldErrors = e.getFieldErrors();
        Map<String, String> errMap = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMap);
    }

    // 500: catch-all for any unexpected exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        System.out.println("in catch-all exc");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(e.getMessage(), "Failed"));
    }
}
