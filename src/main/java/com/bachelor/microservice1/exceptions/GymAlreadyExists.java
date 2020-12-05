package com.bachelor.microservice1.exceptions;

import static com.bachelor.microservice1.exceptions.ErrorMessageConstants.GYM_ALREADY_EXISTS;

public class GymAlreadyExists extends RuntimeException {

    public String message;

    public GymAlreadyExists() {
        this.message = GYM_ALREADY_EXISTS;
    }
}
