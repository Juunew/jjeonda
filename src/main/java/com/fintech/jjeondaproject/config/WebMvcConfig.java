package com.fintech.jjeondaproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fintech.jjeondaproject.auth.JwtInterceptor;

import lombok.RequiredArgsConstructor;
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(jwtInterceptor)
			.addPathPatterns("/auth/**");
	}
	
		
}
