package com.bachelor.microservice1.api;

import com.bachelor.microservice1.exceptions.GymAlreadyExists;
import com.bachelor.microservice1.exceptions.GymDoesNotExist;
import com.bachelor.microservice1.exceptions.OfferNotFound;
import com.bachelor.microservice1.model.Gym;
import com.bachelor.microservice1.model.HttpResponse;
import com.bachelor.microservice1.model.News;
import com.bachelor.microservice1.service.GymsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gyms")
public class GymsController {

    private final GymsService gymsService;

    @Autowired
    public GymsController(GymsService gymsService) {
        this.gymsService = gymsService;
    }

    @GetMapping
    public List<Gym> getAllGyms() {
        return gymsService.getAllGyms();
    }

    @GetMapping("/{id}")
    public Gym getGymByIds(@PathVariable("id") Long gymId) throws GymDoesNotExist {
        return gymsService.getGymById(gymId);
    }

    @GetMapping("/news")
    public List<News> getNews(@RequestHeader("Authorization") String jwt) {
        return this.gymsService.getNews(jwt);
    }

    @GetMapping("/{gymName}/news")
    public List<News> getNewsForGym(@PathVariable("gymName") String gymName) throws GymDoesNotExist {
        return this.gymsService.getNewsForGym(gymName);
    }

    @PostMapping("/{gymName}/news")
    public ResponseEntity<Void> addNewsForGym(@PathVariable("gymName") String gymName, @RequestBody News news) throws GymDoesNotExist {
        this.gymsService.addNewsForGym(news, gymName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new")
    public ResponseEntity<HttpResponse> addGym(@RequestBody Gym gym) throws GymAlreadyExists {
        gymsService.addGym(gym);
        return ResponseEntity.ok().body(new HttpResponse(null, "Gym successfully added", HttpStatus.OK.value()));
    }

    @DeleteMapping("/{gymName}/remove")
    public ResponseEntity<HttpResponse> deleteGym(@PathVariable("gymName") String gymName) throws GymDoesNotExist {
        gymsService.deleteGym(gymName);
        return ResponseEntity.ok().body(new HttpResponse(null, "Gym successfully deleted", HttpStatus.OK.value()));
    }

    @DeleteMapping("/{gymName}/offers/{offerId}")
    public void deleteOfferForGym(@PathVariable("gymName") String gymName, @PathVariable("offerId") Long offerId) throws GymDoesNotExist, OfferNotFound {
        gymsService.deleteOfferForGym(gymName, offerId);
    }
}
