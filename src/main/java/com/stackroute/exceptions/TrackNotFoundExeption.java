package com.stackroute.exceptions;

public class TrackNotFoundExeption extends Exception {
    private String message;

    public TrackNotFoundExeption(String message) {
        super(message);
        this.message = message;
    }

    public TrackNotFoundExeption() {
    }
}
