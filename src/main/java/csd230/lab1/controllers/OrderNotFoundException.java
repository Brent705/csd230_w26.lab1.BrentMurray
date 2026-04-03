package csd230.lab1.controllers;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find order with ID: " + id);
    }
}
