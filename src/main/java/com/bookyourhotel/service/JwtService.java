package com.bookyourhotel.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bookyourhotel.entity.AppUserEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryDuration;

    private Algorithm algorithm;

    private final String USER_NAME = "username";

    @PostConstruct
    public void postConstruct()
    {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(AppUserEntity aue)
    {
        return JWT.create()
                .withClaim(USER_NAME, aue.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryDuration))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUserName(String token)
    {
        DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedJWT.getClaim(USER_NAME).asString();
    }
}
