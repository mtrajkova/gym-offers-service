package com.bachelor.microservice1.service;

import com.gyms.apigyms.exceptions.GymDoesNotExist;
import com.gyms.apigyms.exceptions.OfferForThisGymAlreadyExists;
import com.gyms.apigyms.model.Offer;

import java.util.List;

public interface OffersService {
    List<Offer> getOffersForGym(Long gymId) throws GymDoesNotExist;
    List<Offer> getAllValidOffers();
    void addOfferForGym(Long gymId, Offer offer) throws OfferForThisGymAlreadyExists, GymDoesNotExist;
    Offer getOfferById(Long id);
}
