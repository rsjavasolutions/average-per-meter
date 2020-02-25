package com.rsjava.price.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IndexOutOfBoundsAdvice{

    @ResponseBody
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String indexOutOfBoundsHandler(IndexOutOfBoundsException ex){
        return "No results, change search criteria";
    }
}
