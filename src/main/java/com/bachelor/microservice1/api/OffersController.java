package com.bachelor.microservice1.api;

import com.gyms.apigyms.exceptions.GymDoesNotExist;
import com.gyms.apigyms.exceptions.OfferForThisGymAlreadyExists;
import com.gyms.apigyms.model.Offer;
import com.gyms.apigyms.service.OffersService;
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

    @GetMapping("/{gymId}")
    public List<Offer> getOffersForGym(@PathVariable Long gymId) throws GymDoesNotExist {
        return this.offersService.getOffersForGym(gymId);
    }

    @PostMapping("/{gymId}")
    public void addOfferForGym(@PathVariable Long gymId, @RequestBody Offer offer) throws OfferForThisGymAlreadyExists, GymDoesNotExist {
        offersService.addOfferForGym(gymId, offer);
    }

}