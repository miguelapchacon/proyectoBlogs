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
@Table(schema="SIMAC", name="BLOGS")
@Cacheable(false)
public class Blogs implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="SIMAC.BLOGS_SEQ")
	@SequenceGenerator(name="SIMAC.BLOGS_SEQ", sequenceName="SIMAC.BLOGS_SEQ", allocationSize=1)	
	@Column(name = "ID_BLOG")
	private BigDecimal idBlog;	
	
	@Column(name = "TITLE")
	private String title;	
	
	@Column(name = "DESCRIPTION")
	private String description;

	public Blogs() {
		super();		
	}

	public Blogs(BigDecimal idBlog, String title, String description) {
		super();
		this.idBlog = idBlog;
		this.title = title;
		this.description = description;
	}

	public BigDecimal getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(BigDecimal idBlog) {
		this.idBlog = idBlog;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
		
}
