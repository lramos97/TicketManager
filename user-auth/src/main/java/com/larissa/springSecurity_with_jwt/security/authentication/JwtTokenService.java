package com.larissa.springSecurity_with_jwt.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.larissa.springSecurity_with_jwt.entity.User;
import com.larissa.springSecurity_with_jwt.exceptions.GenerationTokenException;
import com.larissa.springSecurity_with_jwt.exceptions.ValidationTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class JwtTokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.issuer}")
    private String issuer;

    @Value("${api.security.token.expiration-hours}")
    private int expirationHours;

    public String generateToken(User user) {
        try{
            Algorithm algorithm = getAlgorithm();
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getLogin())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new GenerationTokenException();
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = getAlgorithm();
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e) {
            throw new ValidationTokenException();
        }
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }

    private Instant expirationDate() {
        return Instant.now().plus(Duration.ofHours(expirationHours));
    }
}
