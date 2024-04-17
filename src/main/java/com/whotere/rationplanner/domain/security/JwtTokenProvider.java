package com.whotere.rationplanner.domain.security;

import com.whotere.rationplanner.data.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Класс, позволяющий производить различные манипуляции над jwt токенами
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(@NonNull User user, @NonNull Long validityTimeInMinutes) {

        Claims claims = Jwts.claims().setSubject(user.getId());

        Date now = new Date();
        Date validUntil = new Date(now.getTime() + validityTimeInMinutes * 60 * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validUntil)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getSubject(@NonNull String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
