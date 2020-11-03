package com.flipkart.exception;

public class UserNotFoundException extends Exception {

    private String message;

    public UserNotFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
