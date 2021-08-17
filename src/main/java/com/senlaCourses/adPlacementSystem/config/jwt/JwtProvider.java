package com.senlaCourses.adPlacementSystem.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Java web token provider.
 */
@Slf4j
@Component
public class JwtProvider {

  @Value("$(jwt.secretWord)")
  private String jwtSecretWord;

  /**
   * Generates token.
   *
   * @param username username of User.
   * @return generated token.
   */
  public String generateToken(String username) {
    Date date = Date.from(LocalDate.now().plusDays(10)
        .atStartOfDay(ZoneId.systemDefault()).toInstant());

    return Jwts.builder()
        .setSubject(username)
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, jwtSecretWord).compact();
  }

  /**
   * Gets username of user.
   *
   * @param token generated token.
   * @return username of user.
   */
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
      log.error("Token expired");
    } catch (UnsupportedJwtException unsEx) {
      log.error("Unsupported jwt");
    } catch (MalformedJwtException mjEx) {
      log.error("Malformed jwt");
    } catch (SignatureException sEx) {
      log.error("Invalid signature");
    } catch (Exception e) {
      log.error("invalid token");
    }
    return false;
  }
}
