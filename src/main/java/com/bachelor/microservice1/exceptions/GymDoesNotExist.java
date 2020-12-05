package com.bachelor.microservice1.exceptions;

import static com.bachelor.microservice1.exceptions.ErrorMessageConstants.GYM_NOT_FOUND;

public class GymDoesNotExist extends RuntimeException {
    public String message;

    public GymDoesNotExist() {
        this.message = GYM_NOT_FOUND;
    }
}
