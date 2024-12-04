package com.jhb0430.memo.user;

import java.util.HashMap;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.memo.user.domain.User;
import com.jhb0430.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	private UserService userService;
	
//	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String,String> join(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email
			){
		
		Map<String, String> resultMap = new HashMap<>();
		
		// password의 암호화 (그대로 전달하면 보안상 문제가 되니까)
		// 암호화 과정을 수행해서 서비스에 전달 -> 컨트롤러의 역할이 아니다
		// 그럼 서비스로 ? 
		
		if(userService.addUser(loginId, password, name, email)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	
	// 로그인
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			, HttpServletRequest request 
			){
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
			
		if(user != null) {
			
			HttpSession session = request.getSession();
			
			// user id, user name
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
//			session.setAttribute("password", user.getPassword());
			
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
	}
	

	
	
	
	
	
}
