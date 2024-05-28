package com.inn.cafe.JWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
   private String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
   }

   private String createToken(Map<String, Object> claims, String subject){

    return Jwts.builder()
    .claims(claims)   
    .subject(subject)
    .issuedAt(new Date(System.currentTimeMillis()))
    .expiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
    .signWith(getSignKey())
    .compact();
   }

   public String extractUsername(String token){
    return extractClaims(token, Claims::getSubject);
   }

   public Date extractExpiration(String token){
    return extractClaims(token, Claims::getExpiration);
   }

   private <T> T extractClaims(String token, Function<Claims,T> claimsResolvers){
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
   }

   private SecretKey getSignKey(){
    byte[] key = Decoders.BASE64.decode("D40837DD97333B7A78320CE1E244D43BB2C006B4D6FDA78E61DB3E0B467C5958");
    return Keys.hmacShaKeyFor(key);
   }

   private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).getPayload();
   }

   private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
   }



   public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

}
