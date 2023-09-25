package com.blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blogs.entity.Blogs_Readers;

@Repository
public interface ReadersBlogsRepository extends JpaRepository<Blogs_Readers, String> 
{
	@Query(value="select m from Blogs_Readers m")
	List<Blogs_Readers> getAllReadersBlogs();
	
	
	
	
	
}
