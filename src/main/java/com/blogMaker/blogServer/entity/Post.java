package com.blogMaker.blogServer.entity;



import java.util.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity	
@Data
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String name;
	
	
	@Column(length = 5000)
	private String content;
	
	private String postedBy;
	
	private String img;
	
	private Date date;
	
	private int likeCount;
	
	private int viewCount;
	
	@ElementCollection
	@CollectionTable(
	  name = "post_tags",
	  joinColumns = @JoinColumn(name = "post_id")
	)
	@Column(name = "tag")
	private List<String> tags;

	
}
