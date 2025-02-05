package com.semicolon.contact.Management.App.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private Key generateSigninKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateJwtToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 24 * 24 * 60 * 60)))
                .signWith(generateSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(generateSigninKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public String extractSubject(String jwtToken) {
        return extractClaims(jwtToken).getSubject();
    }

    public Date extractExpiration(String jwtToken) {
        return extractClaims(jwtToken).getExpiration();
    }

    public boolean isTokenValid(String jwtToken) {
      return new Date().before(extractExpiration(jwtToken));
    }

}
