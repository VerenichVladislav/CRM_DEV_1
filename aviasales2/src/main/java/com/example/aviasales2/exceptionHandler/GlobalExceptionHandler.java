package com.example.aviasales2.exceptionHandler;

import com.example.aviasales2.exception.GlobalBadRequestException;
import com.example.aviasales2.exception.NoSuchEntityException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GlobalBadRequestException.class)
    protected ResponseEntity <Object> handleGlobalBadRequestException(
            GlobalBadRequestException ex) {
        Map <String, Object> body = new HashMap <>();

        List <String> errors = ex.getResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity <>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchEntityException.class)
    protected ResponseEntity <Object> handleNoSuchEntityException(
            NoSuchEntityException ex) {
        Map <String, Object> body = new HashMap <>();
        String error = "No such " + ex.getAClass().getSimpleName();
        body.put("errors", error);
        body.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity <>(body, HttpStatus.BAD_REQUEST);
    }
}
