package com.tienngv.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler implements AuthenticationEntryPoint {

    private String getMessage(String key, Object... args) {
        try {
            return Objects.requireNonNull(getMessageSource())
                    .getMessage(key, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return "Something is wrong";
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        var message = getMessage("MethodArgumentNotValidException.message");
        var error = new LinkedHashMap<String, String>();
        for (var err : ex.getFieldErrors()) {
            var key = err.getField();
            var value = err.getDefaultMessage();
            error.put(key, value);
        }
        ErrorResponse response = new ErrorResponse(message, error);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        Map<String, Object> body = new HashMap<>();
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", 102);
        errorBody.put("error", "Internal Server Error 102102");
        errorBody.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(errorBody);
    }

    @ExceptionHandler(GpayException.class)
    public ResponseEntity<Map<String, Object>> handleGpayException(GpayException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", ex.getStatus());
        error.put("message", ex.getMessage());
        error.put("data", ex.getData());
        return ResponseEntity.status(HttpStatus.OK).body(error);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", "400");
        error.put("message", ex.getLocalizedMessage());
        error.put("data", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSQLException(SQLException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", "404");
        error.put("message", ex.getLocalizedMessage());
        error.put("data", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.UNAUTHORIZED.value());
        error.put("message", authException.getMessage());

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), error);

    }
}
