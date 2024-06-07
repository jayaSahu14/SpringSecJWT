package com.codewithproj.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTservice {

     String extractUserName(String token);

     String generateToken(UserDetails userDetails) ;

     public boolean isTokenValid(String token, UserDetails userDetails);

      boolean isTokenExpired(String token);

}
