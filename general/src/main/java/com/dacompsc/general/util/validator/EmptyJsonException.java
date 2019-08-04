package com.dacompsc.general.util.validator;

public class EmptyJsonException extends Exception{

    public EmptyJsonException() {
    }

    //Constructor that accepts a message
    public EmptyJsonException(String message) {
        super(message);
    }
}
