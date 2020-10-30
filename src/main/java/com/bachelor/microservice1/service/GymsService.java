package com.bachelor.microservice1.service;


import com.bachelor.microservice1.exceptions.GymAlreadyExists;
import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.model.Gym;
import com.bachelor.microservice1.model.News;

import java.util.List;

public interface GymsService {
    List<Gym> getAllGyms();

    void addGym(Gym gym) throws GymAlreadyExists;

    Gym getGymById(Long id) throws GymDoesNotExist;

    List<Gym> getGymsByLocation(String location);

    List<News> getNews(String jwt);
}
