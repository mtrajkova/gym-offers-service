package com.bachelor.microservice1.exceptions;

public class GymAlreadyExists extends Exception {

    public String message;

    public GymAlreadyExists() {
        this.message = "Gym already exists";
    }
}
