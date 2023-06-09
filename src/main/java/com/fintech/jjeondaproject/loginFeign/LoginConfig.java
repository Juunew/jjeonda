package com.fintech.jjeondaproject.loginFeign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Client;
@Configuration // 스프링의 구성 클래스임을 나타냄
public class LoginConfig {
	@Bean
	public Client feignClient() { // feignClient 생성
		return new Client.Default(null, null);  // 클라이언트의 응답 시간 초과 및 요청 시간 초과에 대한 설정을 지정
												// null 입력시 기본 값
												
	}

}
