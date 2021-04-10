package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.database.entity.Book;
import com.example.demo.service.ExampleService;

/**
 * 
 * 
 *
 */
@Controller
@RequestMapping("/")
public class ExampleController {

	
	@Autowired
	private ExampleService exampleService;
	
	@GetMapping("/index")
	public String index(Model model) {
		
		List<Book> books = exampleService.getBooks();
		model.addAttribute("books", books);
		
		return "index";
	}
	
	
	@PostMapping("/add")
	public String add(@ModelAttribute Book book){
		
		exampleService.addBook(book);
		
		return "redirect:/index";
	}
}
