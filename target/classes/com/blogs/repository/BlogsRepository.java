package com.blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blogs.entity.Blogs;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, String> 
{
	@Query(value="select m from Blogs m")
	List<Blogs> getAllBlogs();
	
	
	@Query(value="SELECT COUNT(ID_BLOG) FROM BLOGS WHERE TITLE=?1", nativeQuery=true)
	public int findExistingName(String nombre);
	
	
	
}
