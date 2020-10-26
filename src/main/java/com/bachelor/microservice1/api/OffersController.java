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

    @GetMapping("/gym/{gymId}/all")
    public List<Offer> getOffersForGym(@PathVariable Long gymId, @RequestHeader("Authorization") String jwt) throws GymDoesNotExist {
        return this.offersService.getOffersForGym(gymId);
    }

    @GetMapping("/gym/{gymId}")
    public List<Offer> getAvailableOffersForGym(@PathVariable Long gymId, @RequestHeader("Authorization") String jwt) throws GymDoesNotExist {
        return this.offersService.getAvailableOffersForGym(gymId);
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable("id") Long offerId, @RequestHeader("Authorization") String jwt) throws OfferNotFound {
        return this.offersService.getOfferById(offerId);
    }

    @PostMapping("/{gymId}")
    public void addOfferForGym(@PathVariable Long gymId, @RequestBody Offer offer, @RequestHeader("Authorization") String jwt) throws OfferForThisGymAlreadyExists, GymDoesNotExist {
        offersService.addOfferForGym(gymId, offer);
    }

}
