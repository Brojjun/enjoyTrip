package com.enjoy.trip.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.enjoy.trip.config.JwtManager;

@Component
public class TokenAuthInterceptor implements HandlerInterceptor {
	private AuthorizationExtractor authExtractor;
	private JwtManager jwtManager;
	
	public TokenAuthInterceptor(AuthorizationExtractor authExtractor, JwtManager jwtManager) {
		this.authExtractor = authExtractor;
		this.jwtManager = jwtManager;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		 System.out.println(">>> interceptor.preHandle 호출");
	     String token = authExtractor.extract(request, "TOKEN ");
	     if (StringUtils.isEmpty(token)) {
	         return true;
	     }

	     if (!jwtManager.validateToken(token)) {
	        throw new IllegalArgumentException("유효하지 않은 토큰");
	     }

	     int no = jwtManager.getUserNoFromToken(token);
	     request.setAttribute("no", no);
	     return true;
	}
}
