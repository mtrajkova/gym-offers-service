package com.bachelor.microservice1.api;

import com.bachelor.microservice1.configuration.security.JWTTokenService;
import com.bachelor.microservice1.configuration.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private JWTTokenService jwtTokenService;

    @GetMapping("/users")
    public String getToken() {
        return jwtTokenService.generateToken(new User(1, "mare", "mare", false));
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "hello admin";
    }
}
