package com.fintech.jjeondaproject.oauth;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
// OauthProperties클래스는 값을 바인딩 할 수 있는 상태로 만든 것이고
// OauthConfig 클래스는 실제로 바인딩하여 OauthProperties 객체로 만들어짐
@Configuration
@EnableConfigurationProperties(OauthProperties.class)
public class OauthConfig {
	
	private final OauthProperties properties;
	
	public OauthConfig(OauthProperties properties) {
		this.properties = properties;
	}

}
