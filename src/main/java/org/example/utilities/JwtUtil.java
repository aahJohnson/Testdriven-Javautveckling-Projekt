package org.example.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
    final private static String phrase ="Johnson";

    public static String generateToken(String username) {
        String token = "";

        Algorithm algorithm = Algorithm.HMAC256(phrase);
        token = JWT.create().withIssuer("auth0").withClaim("username", username).sign(algorithm);

        return token;
    }

    public static boolean validateToken(String username, String token) {
        DecodedJWT jwt;

        Algorithm algorithm = Algorithm.HMAC256(phrase);
        JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("username", username).withIssuer("auth0").build();
        jwt = jwtVerifier.verify(token);

        Claim claim = jwt.getClaim("username");

        return claim != null;
    }
}