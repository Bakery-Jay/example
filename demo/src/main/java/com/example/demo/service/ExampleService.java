package com.example.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.database.entity.Book;

public interface ExampleService {

	public List<Book> getBooks();
	
	@Transactional
	public void addBook(Book book);
}
