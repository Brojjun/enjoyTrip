package com.enjoy.trip.config;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.enjoy.trip.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {
  private final String securityKey = "enjoyTrip"; // TODO 민감정보는 따로 분리하는 것이 좋다
  private final Long expiredTime = 1000 * 60L * 10L; // 유효시간 10분
  private final Long expiredRefreshTime = 1000 * 60L * 60L * 24L; // 유효시간 1일

  /**
   * User 정보를 담은 JWT 토큰을 생성한다.
   *
   * @param User user 정보를 담은 객체
   * @return String JWT 토큰
   */
  public String generateJwtToken(User user) {
    Date now = new Date();
    return Jwts.builder()
        .setSubject(user.getId()) // 보통 user id
        .setHeader(createHeader())
        .setClaims(createClaims(user)) // 클레임, 토큰에 포함될 정보
        .setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
        .signWith(SignatureAlgorithm.HS256, securityKey)
        .compact();
  }
  
  /**
   * User 정보를 담은 JWT 토큰을 생성한다.
   *
   * @param User user 정보를 담은 객체
   * @return String JWT 토큰
   */
  public String generateRefreshJwtToken(User user) {
    Date now = new Date();
    return Jwts.builder()
        .setSubject(user.getId()) // 보통 user id
        .setHeader(createHeader())
        .setClaims(createClaims(user)) // 클레임, 토큰에 포함될 정보
        .setExpiration(new Date(now.getTime() + expiredRefreshTime)) // 만료일
        .signWith(SignatureAlgorithm.HS256, securityKey)
        .compact();
  }

  private Map<String, Object> createHeader() {
    Map<String, Object> header = new HashMap<>();
    header.put("typ", "JWT");
    header.put("alg", "HS256"); // 해시 256 사용하여 암호화
    header.put("regDate", System.currentTimeMillis());
    return header;
  }

  /**
   * 클레임(Claim)을 생성한다.
   *
   * @param member 토큰을 생성하기 위한 계정 정보를 담은 객체
   * @return Map<String, Object> 클레임(Claim)
   */
  private Map<String, Object> createClaims(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getId()); // userId
    claims.put("userNo", user.getNo()); // userNo
    return claims;
  }
  
  /**
   * Token 에서 Claim 을 가져온다.
   *
   * @param token JWT 토큰
   * @return Claims 클레임
   */
  private Claims getClaims(String token) {
    return Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody();
  }

  /**
   * 토큰으로 부터 username 을 가져온다.
   *
   * @param token JWT 토큰
   * @return String Member 의 username
   */
  public String getUserIdFromToken(String token) {
    return (String) getClaims(token).get("userId");
  }

  /**
   * 토큰으로 부터 user no를 가져온다.
   *
   * @param token JWT 토큰
   * @return 
   */
  public int getUserNoFromToken(String token) {
	  return (int) getClaims(token).get("userNo");
  }
  
  /*
   * 토큰 만료기간 확인 
  */
  public boolean validateToken(String token) {
	  try {
          Jws<Claims> claims = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token);
          return !claims.getBody().getExpiration().before(new Date());
      } catch (Exception e) {
          return false;
      }
  }
}