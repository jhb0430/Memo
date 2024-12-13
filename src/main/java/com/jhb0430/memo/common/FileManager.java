package com.jhb0430.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	// 어디에 저장할지 (기본경로)
	// 상수
	public static final String FILE_UPLOAD_PATH = "D:\\jh240729\\spring project\\upload\\memo";
				// final = > 값을 쉽게 다루기 위해 변수로 만들긴 하지만 이 변수값을 변경하지는 못하게 함. final은 변수 ㅣㅇ름을 대문자로 적어준당. 상수화됨. 
	

	public static String saveFile(int userId, MultipartFile file) {
		
		if(file == null) {
			return null;
		}
		
		// 파일 이름 유지
		// 같은 이름의 파일이 전달 될 경우를 대비해서 디렉토리를 만들어서 파일 저장
		// 디렉토리 이름에 사용자 정보 포함
		// 시간을 디렉토리 이름에 포함
		// UNIX TIME : 1970년 1월 1일 부터 흐른 시간을 milli second(1/ 1000초)로 표현한 값
		// ex ) 2_897234346
		
		String directoryName = "/" + userId + "_" +System.currentTimeMillis();
		
		// 디렉토리 만들기
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		// 디렉토리를 만드는 과정에서 발생되는 에러사항을 다 처리해줘야한다.
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		try {
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(filePath);
			
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			
			// 파일 저장 실패
			return null;
		}
		
		//D:\\jh240729\\spring project\\upload\\memo/2_897234346/test.png
		
		// /images/2_897234346/test.png
		
		return "/images" + directoryName +"/" + file.getOriginalFilename();
		
	}
	
	// 삭제 기능
	public static boolean removeFile(String filePath){ // /images/2_897234346/test.png
		
		if(filePath == null) {
			return false;
		}
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images","");
		
		Path path = Paths.get(fullFilePath);
		Path directoryPath = path.getParent(); // 상위 경로가 경로 객체로 리턴된다 
		try {
			Files.delete(path);
			Files.delete(directoryPath);// 디렉토리 경로
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
