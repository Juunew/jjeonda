package com.fintech.jjeondaproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fintech.jjeondaproject.util.jwt.JwtInterceptor;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final JwtInterceptor jwtInterceptor;
	private final UserInfoResolver userInfoResolver;
	private final String[] excludePaths = {"/"};

	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(jwtInterceptor)
//				.addPathPatterns(excludePaths);
			.addPathPatterns("/auth11/**");
				//.addPathPatterns(excludePaths);
	}
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(userInfoResolver);
	}
	@Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
