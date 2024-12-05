package com.jhb0430.memo.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jhb0430.memo.post.domain.Post;
import com.jhb0430.memo.post.repository.PostRepository;

@Service
public class PostService {

	
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	// 제목과 내용을 전달받기
	public boolean addPost(int userId, String title, String contents) {
		// 작성 성공실패여부 판단 
	// 메모와 관련된 기능은 jpa 사용
		// userId = 메모 작성자 ... 의 정보는 로그인 한 사용자의 user의 primary 키 값
		// 로그인 되어있다면 session.userId에 정보가 저장되어 있다.
		
							// 같은 키워드라도 같은 값을 의미하는 것은 아니다. 잘 구분할것
		Post post = Post.builder()
						.userId(userId)
						.title(title)
						.contents(contents)
						.build();
		
		try {
//			Post result = 
			postRepository.save(post);
			
			return true;
			
		} catch(Exception e){
			return false;
		}
		
		
	
	}
	
	
		public List<Post> getPostList(){
		
		return postRepository.findAll();
		
		}	
	
	
	
	
}
