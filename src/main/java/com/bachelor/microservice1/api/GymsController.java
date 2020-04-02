package com.bachelor.microservice1.api;

import com.bachelor.microservice1.exceptions.GymAlreadyExists;
import com.bachelor.microservice1.model.Gym;
import com.bachelor.microservice1.service.GymsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void addGym(@RequestBody Gym gym) throws GymAlreadyExists {
        gymsService.addGym(gym);
    }
}
