package com.bachelor.microservice1.service.impl;

import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.exceptions.OfferNotFound;
import com.bachelor.microservice1.model.Gym;
import com.bachelor.microservice1.model.Offer;
import com.bachelor.microservice1.repository.GymsRepository;
import com.bachelor.microservice1.repository.OffersRepository;
import com.bachelor.microservice1.service.OffersService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffersServiceImpl implements OffersService {

    private final OffersRepository offersRepository;
    private final GymsRepository gymsRepository;

    public OffersServiceImpl(OffersRepository offersRepository, GymsRepository gymsRepository) {
        this.offersRepository = offersRepository;
        this.gymsRepository = gymsRepository;
    }

    @Override
    public List<Offer> getOffersForGym(String gymName) throws GymDoesNotExist {
        Gym foundGym = this.gymsRepository.findAll().stream()
                .map(Gym::nameToLowerCase)
                .filter(gym -> gym.getName().equals(gymName))
                .findFirst()
                .orElseThrow(GymDoesNotExist::new);

        return offersRepository.findAllByGymId(foundGym.getId()).stream()
                .sorted(Comparator.comparingLong(Offer::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Offer> getAllValidOffers() {
        return offersRepository.findAll().stream()
                .filter(Offer::isOfferValid)
                .collect(Collectors.toList());
    }

    @Override
    public void addOfferForGym(String gymName, Offer offer) throws GymDoesNotExist {
        Gym gym = this.gymsRepository.findByName(gymName).orElseThrow(GymDoesNotExist::new);
        offer.setGym(gym);
        if (offer.getEndOfOffer() == null) {
            offer.setEndOfOffer(offer.getStartDate().plusDays(100000));
        } else {
            offer.setEndOfOffer(offer.getEndOfOffer());
        }
        offersRepository.save(offer);
    }

    @Override
    @Transactional
    public Offer getOfferById(Long id) throws OfferNotFound {
        return offersRepository.findById(id).orElseThrow(OfferNotFound::new);
    }

    @Override
    public List<Offer> getHotOffers() {
        List<Offer> validOffers = getAllValidOffers();
        return validOffers.stream()
                .filter(offer -> !offer.getRegularOffer())
                .sorted(Comparator.comparing(Offer::getStartDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Offer> getRegularOffers() {
        List<Offer> validOffers = getAllValidOffers();
        return validOffers.stream()
                .filter(Offer::getRegularOffer)
                .collect(Collectors.toList());
    }

    @Override
    public List<Offer> getAvailableOffersForGym(String gymName) throws GymDoesNotExist {
        return getOffersForGym(gymName).stream()
                .filter(Offer::isOfferValid)
                .collect(Collectors.toList());
    }
}
