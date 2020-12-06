package com.bachelor.microservice1.service;


import com.bachelor.microservice1.exceptions.GymAlreadyExists;
import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.exceptions.OfferNotFound;
import com.bachelor.microservice1.model.Gym;
import com.bachelor.microservice1.model.News;

import java.util.List;

public interface GymsService {
    List<Gym> getAllGyms();

    void addGym(Gym gym) throws GymAlreadyExists;

    Gym getGymById(Long id) throws GymDoesNotExist;

    List<Gym> getGymsByLocation(String location);

    List<News> getNews(String jwt);

    void deleteGym(String gymName) throws GymDoesNotExist;

    void deleteOfferForGym(String gymName, String offerName) throws GymDoesNotExist, OfferNotFound;

    List<News> getNewsForGym(String gymName) throws GymDoesNotExist;

    void addNewsForGym(News news, String gymName) throws GymDoesNotExist;

    void deleteAllOffersForGym(Long gymId) throws GymDoesNotExist;
}
