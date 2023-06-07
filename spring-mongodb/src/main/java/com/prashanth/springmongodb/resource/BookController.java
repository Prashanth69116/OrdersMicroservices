package com.prashanth.springmongodb.resource;

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

import com.prashanth.springmongodb.Exception.BookNotFoundException;
import com.prashanth.springmongodb.Exception.BooksNotFoundException;
import com.prashanth.springmongodb.Exception.DuplicateBookException;
import com.prashanth.springmongodb.model.Book;
import com.prashanth.springmongodb.repository.BookRepository;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository repository;
	

	
	@PostMapping("/addBook")
	/*public String saveBook(@RequestBody Book book)
	{
		if(repository.existsById(book.getId()))
		{
			throw new DuplicateBookException("Book with ID " + book.getId() + " already exists.");
		}
		repository.save(book);
		return "Added Book with id: "+ book.getId();
	}*/
	public ResponseEntity<String> saveBok(@RequestBody Book book)
	{
		try {
			if(repository.existsById(book.getId()))
			{
				throw new DuplicateBookException("Book with ID "+book.getId() +" already exists");
			}
			repository.save(book);
			return new ResponseEntity<>("Added Book with Id: "+book.getId(),HttpStatus.OK);
		}catch(DuplicateBookException ex)
		{
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	
	
	@GetMapping("/findAllBooks")
	/*public List<Book> getBooks()
	{
		if(repository.count()<=0)
		{
			throw new BooksNotFoundException("There is No books in the record.");
		}
		return repository.findAll();
	}*/
	public  ResponseEntity<List<Book>> getBooks()
	{
		try 
		{
			if(repository.count()<=0)
			{
				throw new BooksNotFoundException("There is No books in the record");
			}
			List<Book> book=repository.findAll();
			return new ResponseEntity<>(book, HttpStatus.OK);
		}
		catch(BooksNotFoundException ex)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping("/findAllBooks/{id}")
	/*public Optional<Book> getBooksById(@PathVariable int id)
	{
		if(!(repository.existsById(id)))
		{
			throw new BookNotFoundException("Book with ID " + id + " is not exists.");
		}
		return repository.findById(id);
	}*/
	public ResponseEntity<Optional<Book>> getBooksById(@PathVariable int id)
	{
		try {
			if(!(repository.existsById(id)))
			{
				throw new BookNotFoundException("Book with Id "+ id + " is not exists.");
			}
			Optional<Book> book=repository.findById(id);
			return new ResponseEntity<>(book, HttpStatus.OK);
			
		}catch(BookNotFoundException ex)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	/*public String DeleteBook(@PathVariable int id)
	{
		if(!(repository.existsById(id)))
		{
			throw new BookNotFoundException("Book with ID " + id + " is not exists.");
		}
		repository.deleteById(id);
		return "Book deleted with id: "+id;
	}*/
	
	public ResponseEntity<String> DeleteBook(@PathVariable int id)
	{
		try {
			if(!(repository.existsById(id)))
			{
				throw new BookNotFoundException("Book with Id "+ id + " is not exist.");
			}
			return new ResponseEntity<>("Book is deleted with id : "+id,HttpStatus.OK);
		}catch(BookNotFoundException ex)
		{
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	

}
