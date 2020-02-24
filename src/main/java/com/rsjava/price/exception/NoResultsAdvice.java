package com.rsjava.price.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoResultsAdvice {

    @ResponseBody
    @ExceptionHandler(NoResultsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String noResultsHandler(NoResultsException ex){
        return ex.getMessage();
    }
}
