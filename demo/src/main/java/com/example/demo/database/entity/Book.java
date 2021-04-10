package com.example.demo.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Book {

	
	/**
	 * id 
	 * GenerationType.AUTO => jpa幫你建立id
	 * GenerationType.IDENTITY => database自己建立id 
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * 書名
	 */
    @Column
	private String name;
	
	/**
	 * 作者
	 */
    @Column
	private String author;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	
}
