package com.bachelor.microservice1.service;

import com.bachelor.microservice1.model.UserPrincipal;

public interface TokenService {
    UserPrincipal parseToken(String token);
}
