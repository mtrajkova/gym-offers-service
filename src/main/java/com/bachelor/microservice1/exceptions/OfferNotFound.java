package com.bachelor.microservice1.exceptions;

import static com.bachelor.microservice1.exceptions.ErrorMessageConstants.OFFER_NOT_FOUND;

public class OfferNotFound extends RuntimeException {
    public String message;

    public OfferNotFound() {
        this.message = OFFER_NOT_FOUND;
    }
}
