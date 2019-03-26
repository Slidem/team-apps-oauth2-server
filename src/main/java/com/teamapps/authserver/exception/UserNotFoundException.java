package com.teamapps.authserver.exception;

/**
 * @author Mihai Alexandru
 * @date 24.11.2018
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
