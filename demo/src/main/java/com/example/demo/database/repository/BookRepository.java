package com.example.demo.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.database.entity.Book;

public interface BookRepository extends  JpaRepository<Book, Integer>{

}
