package com.jhb0430.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {

	/*
	 * 메모 입력	/post/create
메모 리스트	/post/list-view
메모 상세	/post/detail-view
	 * */
	
	@GetMapping("/list-view")
	public String memoList() {
		
		return "post/list";
	}
	
	
}
