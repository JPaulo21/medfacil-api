package com.api.medfacil.config.security.JWT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.api.medfacil.domain.entities.User;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenService {
    @Value("${api.security.token.service}")
    private String secretKey;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withIssuer("api-medfacil")
                .withSubject(user.getUsername())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
        return token;
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("api-medfacil")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException e){
            log.warn("Token inválido");
            return "";
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
}
