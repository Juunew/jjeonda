package com.fintech.jjeondaproject.util.jwt;

import com.fintech.jjeondaproject.common.constant.errorType.JwtError;
import com.fintech.jjeondaproject.exception.JwtException;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor{

	private final JwtUtil jwtUtil;

	@Value("${jwt.secret-key}")
	private String secretKey;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		String token = request.getHeader("Authorization");
		Long userId = jwtUtil.getUserId(token, secretKey);

		if (!jwtUtil.validate(token, userId, secretKey) && token == null) {
			throw new JwtException(JwtError.INVALID_JWT_TOKEN);
		}

		return true;
	}


}
