package com.flipkart.exception;

/**
 * The type User not found exception.
 */
public class UserNotFoundException extends Exception {

    private String message;

    /**
     * Instantiates a new User not found exception.
     *
     * @param message the message
     */
    public UserNotFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
