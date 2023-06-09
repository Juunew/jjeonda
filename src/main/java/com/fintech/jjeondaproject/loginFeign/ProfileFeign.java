package com.fintech.jjeondaproject.loginFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;

@FeignClient(name= "kakaoProfile", url = "https://kauth.kakao.com/oauth/authorize")
public interface ProfileFeign {
	@GetMapping(value="/me", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	ProfileResponseDto getProfile(@RequestHeader(value= "Authorization") String accessToken);

}
