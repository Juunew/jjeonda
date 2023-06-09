package com.fintech.jjeondaproject.loginFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fintech.jjeondaproject.dto.user.TokenResponseDto;
// name="feign client 이름 설정", url="호출할 api url", configuration="feignClient설정정보가 셋팅되어있는 클래스)
@FeignClient(name= "kakao", url = "https://kauth.kakao.com/oauth/authorize", configuration = LoginConfig.class)
public interface LoginFeign {
	
	// api를 호출할 메소드 세팅(인가코드받기)
	@GetMapping(value = "/token", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	TokenResponseDto login(@RequestParam("grant_type") String grant_type,
				           @RequestParam("client_id") String client_id,
				           @RequestParam("client_secret") String client_secret,
				           @RequestParam("code") String code,
				           @RequestParam("state") String state);
}
