package com.blogs.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="SIMAC", name="BLOGS_READER")
@Cacheable(false)
public class Blogs_Readers implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="SIMAC.BLOGS_READERS_SEQ")
	@SequenceGenerator(name="SIMAC.BLOGS_READERS_SEQ", sequenceName="SIMAC.BLOGS_READERS_SEQ", allocationSize=1)	
	@Column(name = "ID_BLOGS_READER")
	private BigDecimal idBlogsReader;	
	
	
	@Column(name = "ID_BLOG")
	private BigDecimal idBlog;
	
	@Column(name = "ID_READER")
	private BigDecimal idReader;
	


	public Blogs_Readers() {
		super();
		
	}

	public Blogs_Readers(BigDecimal idBlogsReader, BigDecimal idBlog, BigDecimal idReader) {
		super();
		this.idBlogsReader = idBlogsReader;
		this.idBlog = idBlog;
		this.idReader = idReader;
	}

	
	
	
	
	public BigDecimal getIdBlogsReader() {
		return idBlogsReader;
	}

	public void setIdBlogsReader(BigDecimal idBlogsReader) {
		this.idBlogsReader = idBlogsReader;
	}

	public BigDecimal getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(BigDecimal idBlog) {
		this.idBlog = idBlog;
	}

	public BigDecimal getIdReader() {
		return idReader;
	}

	public void setIdReader(BigDecimal idReader) {
		this.idReader = idReader;
	}


	
	
	
	
	
		
}
