package com.prashanth.springmongodb.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.prashanth.springmongodb.Service.BookService;
import com.prashanth.springmongodb.model.Book;
import com.prashanth.springmongodb.repository.BookRepository;

import com.prashanth.springmongodb.Exception.BookNotFoundException;
import com.prashanth.springmongodb.Exception.BooksNotFoundException;
import com.prashanth.springmongodb.Exception.DuplicateBookException;

@RestController
public class BookController {
	
	
	@Autowired
	private BookService service;
	


	
	@PostMapping("/addBook")
	
	public ResponseEntity<String> saveBook(@RequestBody Book book)
	{
			service.saveBook(book);
			return new ResponseEntity<>("Added Book with Id: "+book.getId(),HttpStatus.OK);
		
	}
	
	/*==========================find All Books=============================*/
	
	@GetMapping("/findAllBooks")
	public ResponseEntity<List<Book>> getBooks()
	{
			return new ResponseEntity<>(service.getBooks(), HttpStatus.OK);
	}
	
	/*=============Getting BooksByID=====================*/
	
	
	@GetMapping("/findAllBooks/{id}")
	public ResponseEntity<Optional<Book>> getBooksById(@PathVariable int id)
	{
		return new ResponseEntity<>(service.getBooksById(id), HttpStatus.OK);
		
	}
	

	@DeleteMapping("/delete/{id}")
	
	public ResponseEntity<String> DeleteBook(@PathVariable int id)
	{
		if(service.getBooks().isEmpty())
		{
			throw new BookNotFoundException("Book with ID " + id + " Not exists in the List.");
		}
			service.DeleteBook(id);
			return new ResponseEntity<>("Book deleted with id: "+id, HttpStatus.OK);
		
	}
	
	
	
	

}
