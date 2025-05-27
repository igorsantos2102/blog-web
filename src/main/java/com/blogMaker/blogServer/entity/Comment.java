package com.blogMaker.blogServer.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String content;
	
	private Date createdAt;
	
	private String postedBy;
	
	
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	
}
