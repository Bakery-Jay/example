package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.database.RepositoryFactory;
import com.example.demo.database.entity.Book;
import com.example.demo.service.ExampleService;

@Service

public class ExampleServiceImpl implements ExampleService {

	@Autowired
	private RepositoryFactory repositoryFactory;

	@Override
	public List<Book> getBooks() {
		return repositoryFactory.getBookRepository().findAll();
	}

	@Override
	@Transactional
	public void addBook(Book book) {

		repositoryFactory.getBookRepository().save(book);

		// 測試 rollback
//		repositoryFactory.getBookRepository().save(book);
//		throw new RuntimeException("error");

	}
}
