package com.bachelor.microservice1.exceptions;

public class GymDoesNotExist extends Exception {
    public String message;

    public GymDoesNotExist() {
        this.message = "Gym does not exist";
    }
}
