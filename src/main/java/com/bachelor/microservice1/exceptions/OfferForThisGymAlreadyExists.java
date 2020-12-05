package com.bachelor.microservice1.exceptions;

import static com.bachelor.microservice1.exceptions.ErrorMessageConstants.OFFER_ALREADY_EXISTS;

public class OfferForThisGymAlreadyExists extends RuntimeException {
    public String message;

    public OfferForThisGymAlreadyExists() {
        this.message = OFFER_ALREADY_EXISTS;
    }
}
