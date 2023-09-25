package com.blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blogs.entity.Readers;

@Repository
public interface ReadersRepository extends JpaRepository<Readers, String> 
{
	@Query(value="select m from Readers m")
	List<Readers> getAllReaders();
	
	
	@Query(value="SELECT COUNT(ID_READER) FROM SIMAC.READERS WHERE NAME_READER=?1", nativeQuery=true)
	public int findExistingName(String nombre);
	
	@Query(value="select m.nameReader from Readers m where m.nameReader=?1")
	public String getuser(String name);
	
	
	
}
