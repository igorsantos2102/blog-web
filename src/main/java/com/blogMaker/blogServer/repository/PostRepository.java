package com.blogMaker.blogServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blogMaker.blogServer.dto.TagCount;
import com.blogMaker.blogServer.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	
	List<Post> findAllByNameContaining(String name);
	
	@Query("SELECT t AS tag, COUNT(p) AS count " +
	           "FROM Post p JOIN p.tags t " +
	           "GROUP BY t " +
	           "ORDER BY COUNT(p) DESC")
	List<TagCount> findTagCounts();

}
