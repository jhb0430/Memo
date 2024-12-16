package com.jhb0430.memo.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			,HttpServletResponse response
			, Object handler
			) throws IOException{
		
		
		HttpSession session = request.getSession();
		
		Integer userId = (Integer)session.getAttribute("userId"); // int로 받으면 값이 있는지 없는지 판단하질 못함... 
		
		String url = request.getRequestURI();
		
		// 로그인이 안된 상태에서 메모와 관련된 기능 페이지의 접근을 막는다
		// 로그인 페이지로 이동

		if(userId == null) {
			//  /post로 시작하는 url path 확인
			
			if(url.startsWith("/post")){
				
				// 리스폰스에는 어떤 정보를 담아야하지?
				// 뭘 줘야 사용자가 로그인 해야한다고 판단할까?
				// 리다이렉트를 통해 로그인 페이지로 이동시키자~!  
				response.sendRedirect("/user/login-view");
				
				return false;
			}
		}
		
		// 로그인이 되어있는 경우 사용자와 관련된 페이지 접근을 막는다
		// 메모 리스트 페이지로 이동한다 

		
		return true;
		
	}

}
