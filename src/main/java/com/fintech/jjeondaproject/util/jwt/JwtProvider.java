package com.fintech.jjeondaproject.util.jwt;
// Jwt를 생성하고 파싱하는 클래스
// AccessToken과 RefreshToken을 생성하고 생성된 토큰을  parsing해서 Claims로 반환하는 메서드 구현
// jjwt라이브러리 사용



import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtProvider {
	
	public static final byte[] secret = "aslkdfjoUdjdhfuskqkljhfuHdksjfLqiwehlknv".getBytes(); // secret key를 byte 배열로 변환
	private final Key key = Keys.hmacShaKeyFor(secret); // Keys.hmacShaKeyFor()를 통해 임의의 비밀키 생성.
														// hmacShaKeyFor는 HMAC-SHA 알고리즘을 통해 secret key에 대해 암호화(서명에 사용)
														// secret key가 32bytes 미만일 경우 WeakKeyException 발생(보안위험)
														// HMAC 알고리즘을 사용하는 경우에는 비밀 키(secret key)를 사용하고, RSA 알고리즘을 사용하는 경우에는 개인 키(private key)를 사용
	
	private String createToken(Map<String, Object> claims, Date expireDate) {
		return Jwts.builder()
				.setClaims(claims) // 토큰에 claim을 설정(claims: payload에 저장되는 클레임정보)
				.setExpiration(expireDate) // 토큰 만료기간 설정
				.signWith(key) // 서명 추가
				.compact(); // 토큰 발행 => JWS
		// JWS : 서버에서 인증을 근거로 인증정보를 서버의 private key로 서명한 것을 토큰화 한 것. // ? private key가 아니고 secret key 아닌가..
	}
	
	public Claims getClaims(String token) { // token에서 claims를 가져오는 로직
		return Jwts.parserBuilder() // JwtParser를 가져옴
				.setSigningKey(key) // 토큰생성할 때 설정한 key를 넣어서
				.build() // build를 통해 JwtParser를 가져오게 되고
				.parseClaimsJws(token) // Jws의 claims를 파싱
				.getBody(); // claims를 얻어옴
	}
	
	public Jwt createJwt(Map<String, Object> claims) {
		String accessToken = createToken(claims, getExpireDateAccessToken());
		String refreshToken = createToken(claims, getExpireDateRefreshToken()); // refreshToken에는 보안상 문제때문에 아무런 Claims를 포함하지 않도록 한다.
		return Jwt.builder()
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.build();
	}

	private Date getExpireDateAccessToken() {
		long expireTimeMils = 1000 * 60 * 60 * 24 * 60;
		return new Date(System.currentTimeMillis() + expireTimeMils);
	}
	
	private Date getExpireDateRefreshToken() {
		long expireTimeMils = 1000L * 60 * 60 * 24 * 60;
		return new Date(System.currentTimeMillis() + expireTimeMils);
	}
	
	public String getJwtFromCookie(HttpServletRequest request) { // 쿠키에서 jwt 추출
		Cookie[] cookies = request.getCookies(); // 쿠키에서 key값이 "JwToken"인 value 가져오기
		if(cookies != null) {
			for(Cookie c : cookies) {
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				if(cookieName.equals("JwToken")) {
					return cookieValue;
				}
			}
		}
		return null;
	}


}
