package com.fintech.jjeondaproject.util.jwt;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor{
	private final JwtProvider jwtProvider;
	private final UserService userService;
	private final UserRepository userRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

		String reqToken = jwtProvider.getJwtFromCookie(request); // 쿠키에서 key값이 "JwToken"인 value 가져오기
		System.out.println("interceptor_UserId:"+jwtProvider.getClaims(reqToken).get("UserId"));

		if (reqToken == null) {
			return false;
		}

		Claims claims = jwtProvider.getClaims(reqToken);

		Long userId = claims.get("UserId", Long.class);

		if (userId == null || !userRepository.existsById(userId) ) {
			return false;
		}

		String accountId = claims.get("UserAccountId", String.class);
		String userName = claims.get("UserName", String.class);

		UserInfo userInfo = UserInfo.of(userId, accountId, userName);

		request.setAttribute("userInfo", userInfo);

		// 엑세스토큰 만료시간을 가져와서 확인하고, 만료가 되었으면 refresh토큰을 발급???
		// accessToken 만료시, 서버에서 만료된 토큰임을 확인하면
		// 서버는 클라이언트에게 refreshToken 요구.
		// 클라이언트는 AccessToken 재발급을 위해 AccessToken과 RefreshToken을 전송
		// 서버는 전달받은 RefreshToken이 유효한지 확인하고, db에 저장해두었던 refreshToken과 비교
		// 유효한 refreshToken이면 accessToken을 재발급.
		// refreshToken도 만료됐다면 로그인을 다시 하고 Access토큰과 Refresh 토큰을 새로 발급

		return true;

	}


}
