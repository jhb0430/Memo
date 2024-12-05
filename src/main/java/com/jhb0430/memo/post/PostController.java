package com.jhb0430.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhb0430.memo.post.domain.Post;
import com.jhb0430.memo.post.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {

	/*
	 * 메모 입력	/post/create
메모 리스트	/post/list-view
메모 상세	/post/detail-view
	 * */
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	
	@GetMapping("/list-view")
	public String memoList( Model model	) {
		
		// 메모 조회
		
		List<Post> memoList = postService.getPostList();
		
		model.addAttribute("memoList",memoList);
		
		
		return "post/list";
	}
	
	
	
	
	
	@GetMapping("/create-view")
	public String createMemo() {
		
		return "post/input";
	}
	
	
}
