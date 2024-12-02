package com.jhb0430.memo.user.service;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhb0430.memo.common.MD5HashingEncoder;
import com.jhb0430.memo.user.repository.UserRepository;

@Service
public class UserService {

//	@Autowired
	private UserRepository userRepository; 
	
	//생성자를 통해서 적용하는 방식 사용해보좌 
	// 생성자로 구성해두면 꼭 오토와이어드(스프링을 이욯한 게 )가 아니어도 활용할 수 있게 된다 ^ㅁ^ 
	// autowired를 위한 생성자만 존재하는 경우 생략 가능 
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(
			String loginId
			,String password
			,String name
			,String email
							){
		
		// password의 암호화 과정을 수행 
		// 여기서 직접 구성을 해도 되지만 서비스에서 처리하기 좀 복잡한 기능들은 서비스에서 직접 처리하기 보다는
		// 클래스를 따로 구성해서 메소드를 구현하고 서비스에서는 그 메소드를 호출하는 것이 더 효율적이다.(지향)
		// common package 에 생성
		String encodingPassword = MD5HashingEncoder.encode(password);
		//
		int count = userRepository.insertUser(loginId, encodingPassword, name, email);
		// 한 행의 정보를 저장한다는 건 이미 알고 있으니까 , 서비스 입장에서는 이걸 좀 더 쉽게 사용하도록 값을 리턴해준다면 
		// 내가 저장한 값이 제대로 저장 됐다 아니다만 전달 받을 수 있어도 ㄱㅊ음 
		if(count == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
