package com.dacompsc.general.util.validator;

/**
 * Created by Hugo on 6/21/2016.
 */
public class InvalidTokenException extends Exception {
    //Parameterless Constructor
    public InvalidTokenException() {
    }

    //Constructor that accepts a message
    public InvalidTokenException(String message) {
        super(message);
    }
}
