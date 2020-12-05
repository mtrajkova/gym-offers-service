package com.bachelor.microservice1.service;

import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.exceptions.OfferForThisGymAlreadyExists;
import com.bachelor.microservice1.exceptions.OfferNotFound;
import com.bachelor.microservice1.model.Offer;

import java.util.List;

public interface OffersService {
    List<Offer> getOffersForGym(String gymName) throws GymDoesNotExist;

    List<Offer> getAllValidOffers();

    void addOfferForGym(String gymName, Offer offer) throws GymDoesNotExist;

    Offer getOfferById(Long id) throws OfferNotFound;

    List<Offer> getHotOffers();

    List<Offer> getRegularOffers();

    List<Offer> getAvailableOffersForGym(String gymName) throws GymDoesNotExist;
}
