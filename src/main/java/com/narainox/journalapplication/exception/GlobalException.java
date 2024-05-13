package com.narainox.journalapplication.exception;

import com.narainox.journalapplication.utils.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlingValidationException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        String message = methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseHandler.generateResponse(message,HttpStatus.BAD_REQUEST,null);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> recordNotFoundException(RecordNotFoundException recordNotFoundException)
    {
        String message = recordNotFoundException.getMessage();
        return ResponseHandler.generateResponse(message,HttpStatus.NOT_FOUND,null);
    }


    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> usernameAlreadyExistsException(UsernameAlreadyExistsException usernameAlreadyExistsException)
    {
        String message = usernameAlreadyExistsException.getMessage();
        return ResponseHandler.generateResponse(message,HttpStatus.CONFLICT,null);
    }


}
