package com.irms.api.service.impl;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.irms.api.dto.AuthorityDto;
import com.irms.api.entity.User;
import com.irms.api.exception.ApiExceptionFactory;
import com.irms.api.service.JwtService;
import com.irms.api.util.ModelConverter;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import jakarta.transaction.Transactional;
import lombok.var;

@Service
public class JwtServiceImpl implements JwtService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImpl.class);
    private static final String INVALID_JWT_VALUE = "Invalid jwt token";

    private static final RSAKey privateKey = generateRSAKey();
    private static final String ISSUER = "www.irms.com";

    @Value("${security.jwt.expiration-time}")
    private long expirationTime;

    @Override
    @Transactional
    public String generateToken(User user) {
        Objects.requireNonNull(user, "user cannot be null");
        JWSSigner signer;
        try {
            signer = new RSASSASigner(privateKey);
        } catch (JOSEException e) {
            throw ApiExceptionFactory.internalServerError(e);
        }
        Set<AuthorityDto> authorities = user.getAuthorities()
                .stream()
                .map(ModelConverter::toAuthorityDto)
                .collect(Collectors.toSet());
        var claims = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(getExpirationDate(expirationTime))
                .claim("roles", authorities)
                .issuer(ISSUER)
                .issueTime(getIssueDate())
                .subject(user.getUsername())
                .build();
        var header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(privateKey.getKeyID())
                .build();
        var signedJWT = new SignedJWT(header, claims);
        try {
            signedJWT.sign(signer);
        } catch (JOSEException | IllegalStateException e) {
            throw ApiExceptionFactory.internalServerError(e);
        }

        return signedJWT.serialize();
    }

    @Override
    public RSAKey getPublicKey() {
        return privateKey.toPublicJWK();
    }

    @Override
    public boolean isTokenValid(String token) {
        if (token == null) {
            return false;
        }
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);
        } catch (ParseException e) {
            throw ApiExceptionFactory.unauthorized(INVALID_JWT_VALUE, e);
        }
        JWSVerifier verifier;
        try {
            verifier = new RSASSAVerifier(getPublicKey());
        } catch (JOSEException e) {
            throw ApiExceptionFactory.internalServerError(e);

        }
        try {
            return signedJWT.verify(verifier)
                    && Objects.nonNull(signedJWT.getHeader())
                    && Objects.nonNull(signedJWT.getPayload())
                    && Objects.nonNull(signedJWT.getJWTClaimsSet())
                    && Objects.nonNull(signedJWT.getJWTClaimsSet().getClaim("role"))
                    && Objects.equals(ISSUER, signedJWT.getJWTClaimsSet().getIssuer())
                    && new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime());
        } catch (JOSEException | ParseException e) {
            throw ApiExceptionFactory.internalServerError(e);
        }
    }

    @Override
    public String extractUsername(String token) {
        if (token == null) {
            throw ApiExceptionFactory.unauthorized(INVALID_JWT_VALUE);
        }
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);
        } catch (ParseException e) {
            throw ApiExceptionFactory.internalServerError(e);
        }
        try {
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            throw ApiExceptionFactory.unauthorized(INVALID_JWT_VALUE, e);
        }
    }

    private Date getExpirationDate(long expiration) {
        LOGGER.info("JWT expiration time: {}", new Date(Instant.now().toEpochMilli() + expiration));
        return new Date(Instant.now().toEpochMilli() + expiration);
    }

    private Date getIssueDate() {
        return new Date(Instant.now().toEpochMilli());
    }

    private static RSAKey generateRSAKey() {
        String keyId = UUID.randomUUID().toString();
        try {
            return new RSAKeyGenerator(2048)
                    .keyID(keyId)
                    .generate();
        } catch (JOSEException e) {
            throw ApiExceptionFactory.internalServerError(e);
        }
    }
}
