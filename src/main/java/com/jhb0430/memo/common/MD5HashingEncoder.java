package com.jhb0430.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingEncoder {

	
	// md5를 통한 암호화 기능 
	//static 메소드 -> 객체 생성 없이도 사용할 수 있게 해줌~! 
	public static String encode(String message){
		
		String result = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
	
			byte[] bytes =  message.getBytes();
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			for(int i = 0; i < digest.length; i++) {
				
				result += Integer.toHexString(digest[i] & 0xff);
				
			}
			
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
