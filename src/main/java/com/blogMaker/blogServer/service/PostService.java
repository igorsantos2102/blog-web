package com.blogMaker.blogServer.service;

import java.util.List;

import com.blogMaker.blogServer.dto.TagCount;
import com.blogMaker.blogServer.entity.Post;

public interface PostService {

	Post savePost(Post post);
	
	List<Post> getAllPosts();
	
	Post getPostById(Long postId);
	
	void likePost (Long postId);
	
	List<Post> searchByName(String name);
	
	long countPosts();
	
	List<TagCount> getTagCounts();
	
	Post updatePost(Long postId, Post postDetails);
	
	void deletePost(Long postId);
}
