package csd230.lab1.controllers;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not find user with ID: " + id);
    }
}
