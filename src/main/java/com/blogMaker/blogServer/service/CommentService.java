package com.blogMaker.blogServer.service;

import java.util.List;

import com.blogMaker.blogServer.entity.Comment;

public interface CommentService {
	
	
	Comment createComment(Long postId, String postedBy, String content);
	
	 List<Comment> getCommentsByPostId(Long postId);

}
