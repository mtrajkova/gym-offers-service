package com.bachelor.microservice1.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
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
