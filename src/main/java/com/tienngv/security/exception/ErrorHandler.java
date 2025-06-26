package com.tienngv.security.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        var message = "Dữ liệu đầu vào không hợp lệ !";
        var error = new LinkedHashMap<String, String>();
        for (var err : ex.getFieldErrors()) {
            var key = err.getField();
            var value = err.getDefaultMessage();
            error.put(key, value);
        }
        ErrorResponse response = new ErrorResponse(message, error);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
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

}
