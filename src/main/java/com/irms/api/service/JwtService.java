package com.irms.api.service;

import com.irms.api.entity.User;
import com.nimbusds.jose.jwk.RSAKey;

public interface JwtService {
    String generateToken(User details);

    RSAKey getPublicKey();

    boolean isTokenValid(String token);

    String extractUsername(String token);
}
