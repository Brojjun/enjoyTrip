package com.enjoy.trip.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final TokenAuthInterceptor tokenAuthInterceptor;
	
	public WebMvcConfig(TokenAuthInterceptor tokenAuthInterceptor) {
		this.tokenAuthInterceptor = tokenAuthInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenAuthInterceptor).addPathPatterns("/api/user/info/*");
		registry.addInterceptor(tokenAuthInterceptor).addPathPatterns("api/user/refresh");
	}
}

