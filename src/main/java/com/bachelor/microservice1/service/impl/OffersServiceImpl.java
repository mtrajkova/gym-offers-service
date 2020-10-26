package com.bachelor.microservice1.service.impl;

import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.exceptions.OfferForThisGymAlreadyExists;
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
    public List<Offer> getAvailableOffersForGym(Long gymId) throws GymDoesNotExist {
        return getOffersForGym(gymId).stream()
                .filter(Offer::isOfferValid)
                .collect(Collectors.toList());
    }
}
