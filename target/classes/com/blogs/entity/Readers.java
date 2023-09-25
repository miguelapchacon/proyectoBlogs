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
@Table(schema="SIMAC", name="READERS")
@Cacheable(false)
public class Readers implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="SIMAC.READERS_SEQ")
	@SequenceGenerator(name="SIMAC.READERS_SEQ", sequenceName="SIMAC.READERS_SEQ", allocationSize=1)	
	
	@Column(name = "ID_READER")
	private BigDecimal idReaders;
	
	
	@Column(name = "NAME_READER")
	private String nameReader;
	
	
	@Column(name = "AGE")
	private int age;

	public Readers() {
		super();
		
	}
	public Readers(BigDecimal idReaders, String nameReader, int age) {
		super();
		this.idReaders = idReaders;
		this.nameReader = nameReader;
		this.age = age;
	}
	public BigDecimal getIdReaders() {
		return idReaders;
	}
	public void setIdReaders(BigDecimal idReaders) {
		this.idReaders = idReaders;
	}
	public String getNameReader() {
		return nameReader;
	}
	public void setNameReader(String nameReader) {
		this.nameReader = nameReader;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
	
	
	
		
}
