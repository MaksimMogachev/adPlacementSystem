package com.senlacourses.electronicHotelAdministrator.config.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

  @Value("$(jwt.secretWord)")
  private String jwtSecretWord;

  public String generateToken(String login) {
    Date date = Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant());

    return Jwts.builder()
            .setSubject(login)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, jwtSecretWord).compact();
  }

  public String getLoginFromToken(String token) {
    return Jwts.parser()
            .setSigningKey(jwtSecretWord)
            .parseClaimsJws(token)
            .getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecretWord).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expEx) {
      logger.error("Token expired");
    } catch (UnsupportedJwtException unsEx) {
      logger.error("Unsupported jwt");
    } catch (MalformedJwtException mjEx) {
      logger.error("Malformed jwt");
    } catch (SignatureException sEx) {
      logger.error("Invalid signature");
    } catch (Exception e) {
      logger.error("invalid token");
    }
    return false;
  }
}
