package com.fintech.jjeondaproject.config;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.config.annotation.InfoUser;
import com.fintech.jjeondaproject.util.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class UserInfoResolver implements HandlerMethodArgumentResolver {

    private final HttpServletRequest request;
    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(InfoUser.class);
        boolean isCorrectType = UserInfo.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isCorrectType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = jwtProvider.getJwtFromCookie(request);
        Claims claims = jwtProvider.getClaims(token);

        return UserInfo.of(
                claims.get("UserId", Long.class),
                claims.get("UserName", String.class),
                claims.get("UserEmail", String.class)
        );
    }
}
