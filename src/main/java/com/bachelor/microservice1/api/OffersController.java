package com.bachelor.microservice1.api;

import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.exceptions.OfferForThisGymAlreadyExists;
import com.bachelor.microservice1.exceptions.OfferNotFound;
import com.bachelor.microservice1.model.Offer;
import com.bachelor.microservice1.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OffersController {

    private final OffersService offersService;

    @Autowired
    public OffersController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping
    public List<Offer> getAllValidOffers() {
        return offersService.getAllValidOffers();
    }

    @GetMapping("/gym/{gymId}")
    public List<Offer> getOffersForGym(@PathVariable Long gymId) throws GymDoesNotExist {
        return this.offersService.getOffersForGym(gymId);
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable("id") Long offerId) throws OfferNotFound {
        return this.offersService.getOfferById(offerId);
    }

    @PostMapping("/{gymId}")
    public void addOfferForGym(@PathVariable Long gymId, @RequestBody Offer offer) throws OfferForThisGymAlreadyExists, GymDoesNotExist {
        offersService.addOfferForGym(gymId, offer);
    }

}
