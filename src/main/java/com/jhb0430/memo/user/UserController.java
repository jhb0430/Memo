package com.jhb0430.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/join-view")
	public String inputJoin() {
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String inputLogin() {
		return "user/login";
	}
	
	// 회원가입 하는 기능 만들기
//	@GetMapping("/create")
//	public joinUser{
//		
//		return "user/join";
//	}
//	
	
	//로그인 하는 기능 만들기 
	
}
