package com.codewithproj.security.service.impl;


import com.codewithproj.security.service.JWTservice;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTserviceImpl implements JWTservice {

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private <T>T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigninKey() {
        byte[] key= Decoders.BASE64.decode("4f1feeca525de4cdb064656007da3edac7895a87ff0ea865693300fb8b6e8f9c");
        return Keys.hmacShaKeyFor(key);

    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
            String userName=extractUserName(token);
            return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
