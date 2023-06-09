package com.fintech.jjeondaproject.oauth;
// yml파일에 있는 정보들을 객체로 바인딩하는 클래스 -> 값을 바인딩 할 수 있는 상태로 만든 것

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@ConfigurationProperties(prefix = "oauth2") //프로퍼티 값을 객체로 바인딩
public class OauthProperties {
	private final Map<String, User> user = new HashMap<>();
	private final Map<String, Provider> provider = new HashMap<>();
	
	@Getter
	@Setter
	public static class User{
		private String clientId;
		private String clientSerect;
		private String redirectUri;
	}
	
	@Getter
	@Setter
	public static class Provider{
		private String tokenUri;
		private String userInfoUri;
		private String userNameAttribute;
	}
}
