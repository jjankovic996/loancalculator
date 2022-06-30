package com.leanpay.com.loancalculator.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class LoanExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    protected ResponseEntity<String> handleException(
            Exception ex) {

        return new ResponseEntity<>("Exception happened", BAD_REQUEST);
    }
}
