package osu.damek.usedcars.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import osu.damek.usedcars.model.User;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${application.jwt.secret-key}")
    private String jwtSecret;

    @Value("${application.jwt.token-expiration-after-days}")
    private int jwtExpirationDays;

    public String generateJwtToken(Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationDays))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
