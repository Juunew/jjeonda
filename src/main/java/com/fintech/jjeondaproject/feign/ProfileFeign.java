package com.fintech.jjeondaproject.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;

@FeignClient(name= "naverProfile", url = "https://openapi.naver.com/v1/nid")
public interface ProfileFeign {
	@GetMapping(value="/me", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	ProfileResponseDto getProfile(@RequestHeader(value= "Authorization") String accessToken);

}
