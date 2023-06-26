package com.fintech.jjeondaproject.config;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.config.annotation.InfoUser;
import com.fintech.jjeondaproject.util.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    private final JwtUtil jwtUtil;

    @Value("${jwt.secret-key}")
    private String key;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(InfoUser.class);
        boolean isCorrectType = UserInfo.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isCorrectType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = request.getHeader("Authorization");

        return jwtUtil.parseTokenToUserInfo(token, key);
    }
}
