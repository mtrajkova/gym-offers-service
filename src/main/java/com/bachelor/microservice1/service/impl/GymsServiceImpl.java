package com.bachelor.microservice1.service.impl;

import com.bachelor.microservice1.exceptions.GymAlreadyExists;
import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.model.Gym;
import com.bachelor.microservice1.model.News;
import com.bachelor.microservice1.repository.GymsRepository;
import com.bachelor.microservice1.repository.NewsRepository;
import com.bachelor.microservice1.service.GymsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymsServiceImpl implements GymsService {

    private final GymsRepository gymsRepository;
    private final NewsRepository newsRepository;

    public GymsServiceImpl(GymsRepository gymsRepository, NewsRepository newsRepository) {
        this.gymsRepository = gymsRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<Gym> getAllGyms() {
        return gymsRepository.findAll();
    }

    @Override
    public void addGym(Gym gym) throws GymAlreadyExists {
        if (gymsRepository.findByName(gym.getName()).isPresent()) {
            throw new GymAlreadyExists();
        }
        gymsRepository.save(gym);
    }

    @Override
    public Gym getGymById(Long id) throws GymDoesNotExist {
        return gymsRepository.findById(id).orElseThrow(GymDoesNotExist::new);
    }

    @Override
    public List<Gym> getGymsByLocation(String location) {
        return null;
    }

    @Override
    public List<News> getNews(String jwt) {
        return this.newsRepository.findAll();
    }
}
