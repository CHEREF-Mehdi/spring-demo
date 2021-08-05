package com.api.demo.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${JWT_SECRET_KEY}")
  private String SECRET_KEY;

  private Claims extractAllClaims(String token) {
    return Jwts
      .parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody();
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String createToken(Map<String, Object> claims, String subject) {
    final long currentTimeMillis = System.currentTimeMillis();
    final int duration = 1000 * 60 * 60 * 10;
    return Jwts
      .builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(new Date(currentTimeMillis))
      .setExpiration(new Date(currentTimeMillis + duration))
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact();
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (
      !isTokenExpired(token) && username.equals(userDetails.getUsername())
    );
  }
}
