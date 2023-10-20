package com.neerajkumar.studentmanagementsystem.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.neerajkumar.studentmanagementsystem.payloads.Response;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFoundExceptionHandler(ResourceNotFoundException exp) {
        String msg = exp.getMessage();
        Response response = new Response(msg,false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Response> resourceAlreadyExistsExceptionHandler(ResourceAlreadyExistsException exp) {
        String msg = exp.getMessage();
        Response response = new Response(msg,false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exp) {
        Map<String,String> mp = new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            mp.put(fieldName, message);
        });
        return new ResponseEntity<>(mp,HttpStatus.BAD_REQUEST);
    }
}
