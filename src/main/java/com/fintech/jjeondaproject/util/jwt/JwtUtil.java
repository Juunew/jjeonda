package com.fintech.jjeondaproject.util.jwt;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import com.fintech.jjeondaproject.common.UserInfo;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

//TODO: test 후 수정
@Component
public class JwtUtil {

    // 로그인 성공 시 AccessToken 생성
    public JwtTokenDto generateAccessToken(UserInfo userInfo, String key, long expireTimeMs, long reExpireTimeMs) {
        return doGenerateToken(userInfo, expireTimeMs, key, reExpireTimeMs);
    }

    // JWT token 생성 메소드
    private JwtTokenDto doGenerateToken(UserInfo userInfo, long expireTime, String key, long reExpireTimeMs) {
        String accessToken = createAccessToken(userInfo, expireTime, key);
        String refreshToken = createRefreshToken(userInfo, reExpireTimeMs, key);

        return JwtTokenDto.of(accessToken, refreshToken);
    }

    // AccessToken
    private String createAccessToken(UserInfo userInfo, long expireTime, String key) {
        return Jwts.builder()
                .setClaims(setClaims(userInfo))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    // RefreshToken
    private String createRefreshToken(UserInfo userInfo, long expireTime, String key) {
        return Jwts.builder()
                .setClaims(setClaims(userInfo))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims setClaims(UserInfo userInfo) {
        Claims claims = Jwts.claims();

        claims.put("userId", userInfo.getUserId());
        claims.put("accountId", userInfo.getAccountId());
        claims.put("userName", userInfo.getUserName());

        return claims;
    }

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 지워도 될 것 같은디? 다시 check
    public UserInfo parseTokenToUserInfo(String token, String key) {
        Claims claims = extractAllClaims(token, key);

        return UserInfo.of(
                claims.get("userId", Long.class),
                claims.get("accountId", String.class),
                claims.get("userName", String.class)
        );
    }

    // ===== 검증 ===== //
    public Boolean validate(String token, Long tokenUserId, String key) {
        Long userId = getUserId(token, key);
        return userId.equals(tokenUserId) && !isTokenExpired(token, key);
    }

    public Long getUserId(String token, String key) {
        return extractAllClaims(token, key).get("userId", Long.class);
    }

    public Claims extractAllClaims(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token, String key) {
        Date expiration = extractAllClaims(token, key).getExpiration();
        return expiration.before(new Date());
    }
}
