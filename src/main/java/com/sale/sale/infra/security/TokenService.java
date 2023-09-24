package com.sale.sale.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sale.sale.domain.entities.Customer;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final String secret = "edusantanawrasassf";
    public String generateToken(Customer customer) throws  RuntimeException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("auth").withSubject(customer.getEmail()).sign(algorithm);
        } catch (JWTCreationException err) {
            throw new RuntimeException("Error when try create an access token ", err);
        }
    }

    public String validToken(String token){
          try {
              Algorithm algorithm = Algorithm.HMAC256(secret);
              return JWT.require(algorithm).withIssuer("auth").build().verify(token).getSubject();
          }catch (JWTVerificationException err){
              return "";
          }
    }
}

