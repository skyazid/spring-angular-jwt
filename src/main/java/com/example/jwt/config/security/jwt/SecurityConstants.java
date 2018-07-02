package com.example.jwt.config.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;

public interface SecurityConstants {

    long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    String SECRET = "SECRET_EXAMPLE_KEY";
    String TOKEN_PREFIX = "Bearer ";
    String AUTHORIZATION_HEADER = "Authorization";
    SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    boolean CORS = false;
    String[] CORS_URL = {
            "http://localhost:4200",
            "http://127.0.0.1:4200"
    };

}
