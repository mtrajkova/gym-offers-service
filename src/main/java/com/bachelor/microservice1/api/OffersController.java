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
    public List<Offer> getAllValidOffers(@RequestHeader("Authorization") String jwt) {
        return offersService.getAllValidOffers();
    }

    @GetMapping("/hot")
    public List<Offer> getHotOffers(@RequestHeader("Authorization") String jwt) {
        return offersService.getHotOffers();
    }

    @GetMapping("/regular")
    public List<Offer> getRegularOffers(@RequestHeader("Authorization") String jwt) {
        return offersService.getRegularOffers();
    }

    @GetMapping("/gym/{gymName}/all")
    public List<Offer> getOffersForGym(@PathVariable String gymName) throws GymDoesNotExist {
        return this.offersService.getOffersForGym(gymName);
    }

    @GetMapping("/gym/{gymName}")
    public List<Offer> getAvailableOffersForGym(@PathVariable String gymName) throws GymDoesNotExist {
        return this.offersService.getAvailableOffersForGym(gymName);
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable("id") Long offerId, @RequestHeader("Authorization") String jwt) throws OfferNotFound {
        return this.offersService.getOfferById(offerId);
    }

    @PostMapping("/new/{gymId}")
    public void addOfferForGym(@PathVariable Long gymId, @RequestBody Offer offer, @RequestHeader("Authorization") String jwt) throws OfferForThisGymAlreadyExists, GymDoesNotExist {
        offersService.addOfferForGym(gymId, offer);
    }

}
