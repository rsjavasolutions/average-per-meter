package com.rsjava.price.exception;

public class NoResultsException extends RuntimeException {

    public NoResultsException() {
        super("No resluts. Change criteria");
    }
}
