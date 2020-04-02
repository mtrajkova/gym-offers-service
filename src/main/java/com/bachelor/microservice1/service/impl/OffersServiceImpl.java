package com.bachelor.microservice1.service.impl;

import com.gyms.apigyms.exceptions.GymDoesNotExist;
import com.gyms.apigyms.exceptions.OfferForThisGymAlreadyExists;
import com.gyms.apigyms.model.Offer;
import com.gyms.apigyms.repository.GymsRepository;
import com.gyms.apigyms.repository.OffersRepository;
import com.gyms.apigyms.service.OffersService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<Offer> getOffersForGym(Long gymId) throws GymDoesNotExist {
        gymsRepository.findById(gymId).orElseThrow(GymDoesNotExist::new);

        return offersRepository.findAllByGymId(gymId);
    }

    @Override
    public List<Offer> getAllValidOffers() {
        return offersRepository.findAll().stream()
                .filter(Offer::isOfferValid)
                .collect(Collectors.toList());
    }

    @Override
    public void addOfferForGym(Long gymId, Offer offer) throws OfferForThisGymAlreadyExists, GymDoesNotExist {
        if (offersRepository.findByNameAndAndDurationInDaysAndPriceAndGymId(
                offer.getName(), offer.getDurationInDays(), offer.getPrice(), gymId
        ).isPresent()) {
            throw new OfferForThisGymAlreadyExists();
        }

        offer.setGym(gymsRepository.findById(gymId).orElseThrow(GymDoesNotExist::new));
        offer.setEndOfOffer(offer.getStartDate().plusDays(offer.getValidityInDays()));
        offersRepository.save(offer);
    }

    @Override
    public Offer getOfferById(Long id) {
        return null;
    }
}
