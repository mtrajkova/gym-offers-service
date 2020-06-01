package com.bachelor.microservice1.configuration.security;

public interface TokenService {
    String generateToken(User user);
    UserPrincipal parseToken(String token);
}
