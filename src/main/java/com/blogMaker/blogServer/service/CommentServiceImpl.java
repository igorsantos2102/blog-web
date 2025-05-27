package com.blogMaker.blogServer.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogMaker.blogServer.entity.Comment;
import com.blogMaker.blogServer.entity.Post;
import com.blogMaker.blogServer.repository.CommentRepository;
import com.blogMaker.blogServer.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public Comment createComment(Long postId, String postedBy, String content) {
		Optional<Post> optionalPost = postRepository.findById(postId);
		
		if(optionalPost.isPresent()) {
			Comment comment = new Comment();
			
			comment.setPost(optionalPost.get());
			comment.setContent(content);
			comment.setPostedBy(postedBy);
			comment.setCreatedAt(new Date());
			
			return commentRepository.save(comment);

		}
		
		throw new EntityNotFoundException("Postagem não encontrada");
	}

	
	public List<Comment> getCommentsByPostId(Long postId) {
		return commentRepository.findByPostId(postId);
	}
}
