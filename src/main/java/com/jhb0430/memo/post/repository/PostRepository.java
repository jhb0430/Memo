package com.jhb0430.memo.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhb0430.memo.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
