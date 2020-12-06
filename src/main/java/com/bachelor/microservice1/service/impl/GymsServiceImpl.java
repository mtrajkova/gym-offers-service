package com.bachelor.microservice1.service.impl;

import com.bachelor.microservice1.exceptions.GymAlreadyExists;
import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.exceptions.OfferNotFound;
import com.bachelor.microservice1.model.Gym;
import com.bachelor.microservice1.model.News;
import com.bachelor.microservice1.model.Offer;
import com.bachelor.microservice1.repository.GymsRepository;
import com.bachelor.microservice1.repository.NewsRepository;
import com.bachelor.microservice1.repository.OffersRepository;
import com.bachelor.microservice1.service.GymsService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymsServiceImpl implements GymsService {

    private final GymsRepository gymsRepository;
    private final OffersRepository offersRepository;
    private final NewsRepository newsRepository;

    public GymsServiceImpl(GymsRepository gymsRepository, OffersRepository offersRepository, NewsRepository newsRepository) {
        this.gymsRepository = gymsRepository;
        this.offersRepository = offersRepository;
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

    @Override
    public void deleteGym(String gymName) throws GymDoesNotExist {
        Gym gym = gymsRepository.findByName(gymName).orElseThrow(GymDoesNotExist::new);
        this.deleteAllOffersForGym(gym.getId());
        gymsRepository.delete(gym);
    }

    @Override
    public void deleteOfferForGym(String gymName, String offerName) throws GymDoesNotExist, OfferNotFound {
        gymsRepository.findByName(gymName).orElseThrow(GymDoesNotExist::new);
        Offer gymOffer = offersRepository.findByNameAndGymName(offerName, gymName).orElseThrow(OfferNotFound::new);
        offersRepository.delete(gymOffer);
    }

    @Override
    public List<News> getNewsForGym(String gymName) throws GymDoesNotExist {
        gymsRepository.findByName(gymName).orElseThrow(GymDoesNotExist::new);
        return newsRepository.findAllByGymName(gymName).stream()
                .sorted(Comparator.comparingLong(News::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void addNewsForGym(News news, String gymName) throws GymDoesNotExist {
        Gym gym = gymsRepository.findByName(gymName).orElseThrow(GymDoesNotExist::new);
        news.setGym(gym);
        newsRepository.save(news);
    }

    @Override
    public void deleteAllOffersForGym(Long gymId) throws GymDoesNotExist {
        this.gymsRepository.findById(gymId).orElseThrow(GymDoesNotExist::new);
        this.offersRepository.deleteAllByGymId(gymId);
    }
}
