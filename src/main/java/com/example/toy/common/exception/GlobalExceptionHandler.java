package com.example.toy.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = {"com.example.toy"})
public class GlobalExceptionHandler {
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<Map<String, Object>> handleCustomException(CustomException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("code", ex.getCode());
    errorResponse.put("message", ex.getMessage());
    return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity globalException(Exception ex, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }
}
