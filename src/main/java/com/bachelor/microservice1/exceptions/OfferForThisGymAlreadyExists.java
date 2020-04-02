package com.bachelor.microservice1.exceptions;

public class OfferForThisGymAlreadyExists extends Exception {
    public String message;

    public OfferForThisGymAlreadyExists() {
        this.message = "This offer for this gym already exists";
    }
}
