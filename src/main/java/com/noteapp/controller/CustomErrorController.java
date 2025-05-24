package com.noteapp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        HttpStatus status = getStatus(request);
        
        // Get error attributes
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String errorClass = throwable != null ? throwable.getClass().getName() : null;
        String errorMessage = throwable != null ? throwable.getMessage() : message;
        
        // Log the error
        logger.error("Error occurred: {} - {}", errorClass, errorMessage, throwable);
        
        errorDetails.put("status", status.value());
        errorDetails.put("error", status.getReasonPhrase());
        errorDetails.put("message", errorMessage != null ? errorMessage : "The requested resource is not available");
        errorDetails.put("path", request.getAttribute("javax.servlet.error.request_uri"));
        errorDetails.put("exception", errorClass);
        
        if (throwable != null) {
            errorDetails.put("trace", throwable.getStackTrace());
        }
        
        return new ResponseEntity<>(errorDetails, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
} 