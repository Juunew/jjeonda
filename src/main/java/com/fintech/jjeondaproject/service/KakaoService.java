package com.fintech.jjeondaproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;
import com.fintech.jjeondaproject.loginFeign.LoginFeign;
import com.fintech.jjeondaproject.loginFeign.ProfileFeign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {
	private final LoginFeign loginFeign;
	private final ProfileFeign profileFeign;
	
	@Value("${oauth.kakao.client-id}")
    private String client_id;
    @Value("${oauth.kakao.client-secret}")
    private String client_secret;
    @Value("${oauth.kakao.redirect-uri}")
    private String redirect_uri;
    
    public String getRequireUrl() { // 인가코드받기
    	String reqUrl = "https://kauth.kakao.com/oauth/authorize?"
    			+ "response_type=code&client_id="+client_id+"&redirect_uri="+redirect_uri;
    	return reqUrl;
    }
    
	/*
	 * public UserInfoFeignDto loginService(String code, String state) { }
	 */
}
