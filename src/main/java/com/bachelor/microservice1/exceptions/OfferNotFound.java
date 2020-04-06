package com.bachelor.microservice1.exceptions;

public class OfferNotFound extends Exception {
    public String message;

    public OfferNotFound() {
        this.message = "This offer does not exists";
    }
}
