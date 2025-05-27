package com.blogMaker.blogServer.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blogMaker.blogServer.dto.TagCount;
import com.blogMaker.blogServer.entity.Post;
import com.blogMaker.blogServer.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public Post savePost(Post post) {
	    // 1) pega o e-mail do token
	    String email = SecurityContextHolder
	                     .getContext()
	                     .getAuthentication()
	                     .getName();

	    // 2) grava esse e-mail em postedBy
	    post.setPostedBy(email);

	    // 3) campos restantes
	    post.setLikeCount(0);
	    post.setViewCount(0);
	    post.setDate(new Date());
	    return postRepository.save(post);
	}

	
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
	
	public Post getPostById(Long postId) {
		Optional<Post> optionalPost = postRepository.findById(postId);
		
		if (optionalPost.isPresent()) {
			Post post = optionalPost.get();
			post.setViewCount(post.getViewCount() + 1);
			
			return postRepository.save(post);
		} else {
			throw new EntityNotFoundException("Postagem não encontrada");
		}
	}
	
	public void likePost (Long postId) {
		Optional<Post> optionalPost = postRepository.findById(postId);

		if(optionalPost.isPresent()){
			Post post = optionalPost.get();
			post.setLikeCount(post.getLikeCount()+1);
			postRepository.save(post);

		} else{

			throw new EntityNotFoundException("Postagem não encontrada com o ID: " + postId);

		}
	}
	
	public List<Post> searchByName(String name) {
		return postRepository.findAllByNameContaining(name);
	}
	
	@Override
    public long countPosts() {
        return postRepository.count();
    }
	
	@Override
	public List<TagCount> getTagCounts() {
	  return postRepository.findTagCounts();
	}
	
	
	@Override
	public Post updatePost(Long postId, Post postDetails) {
	    Post existing = postRepository.findById(postId)
	        .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

	    
	    String currentUser = SecurityContextHolder.getContext()
	                          .getAuthentication().getName();
	    if (!existing.getPostedBy().equals(currentUser)) {
	        throw new AccessDeniedException("Você não pode editar este post");
	    }

	    
	    existing.setName(postDetails.getName());
	    existing.setContent(postDetails.getContent());
	    existing.setImg(postDetails.getImg());
	    existing.setTags(postDetails.getTags());
	    

	    return postRepository.save(existing);
	}

	@Override
	public void deletePost(Long postId) {
	    Post existing = postRepository.findById(postId)
	        .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

	    String currentUser = SecurityContextHolder.getContext()
	                          .getAuthentication().getName();
	    if (!existing.getPostedBy().equals(currentUser)) {
	        throw new AccessDeniedException("Você não pode deletar este post");
	    }

	    postRepository.delete(existing);
	}

	
}
