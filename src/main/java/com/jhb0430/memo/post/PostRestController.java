package com.jhb0430.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jhb0430.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/create")
	public Map<String, String>  createMemo(
			@RequestParam("title") String title
			,@RequestParam("contents") String contents
			,@RequestParam("file") MultipartFile file
			, HttpSession session
			){
		
		
		int userId = (Integer)session.getAttribute("userId"); // 업캐스팅 된 객체 -> 다운캐스팅 해서 써줘야함
		
		
		Map<String, String> resultMap = new HashMap<>();
		
			if(postService.addPost(userId, title, contents,file)) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
				
			}
		
			return resultMap;
		
	}
	
}
