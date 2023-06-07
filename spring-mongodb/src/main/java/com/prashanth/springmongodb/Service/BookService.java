package com.prashanth.springmongodb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prashanth.springmongodb.Exception.BookNotFoundException;
import com.prashanth.springmongodb.Exception.BooksNotFoundException;
import com.prashanth.springmongodb.Exception.DuplicateBookException;
import com.prashanth.springmongodb.model.Book;
import com.prashanth.springmongodb.repository.BookRepository;




@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	public String saveBook(Book book)
	{
		if(repository.existsById(book.getId()))
		{
			throw new DuplicateBookException("Book with ID " + book.getId() + " already exists.");
		}
		repository.save(book);
		return "Added Book with id: "+ book.getId();
	}
	
	
	public  List<Book> getBooks()
	{
		if(repository.count()<=0)
		{
			throw new BooksNotFoundException("There is No books in the record.");
		}
			 return repository.findAll();
	}
	
	/*------------gettting Book details By Id-------------------------------*/
	
	public Optional<Book> getBooksById(int id)
	{
		if(!(repository.existsById(id)))
		{
			throw new BookNotFoundException("Book with ID " + id + " Not exists in the List.");
		}
		return repository.findById(id);
		
	}
	
	
	
	/*-----------Deleting the Books-----------------*/
	
	/*public String DeleteBook(int id)
	{
		if(!(repository.existsById(id)))
		{
			throw new BookNotFoundException("Book with ID " + id + " Not exists in the List.");
		}
		repository.deleteById(id);
		return "Book deleted with id: "+id;
	}*/
	public ResponseEntity<String> DeleteBook(int id)
	{
		try {
				if(repository.count()==0)
				{
				 throw new BookNotFoundException("Book with ID " + id + " Not exists in the List.");
				}
				repository.deleteById(id);
				return new ResponseEntity<>("Book deleted with id: "+id,HttpStatus.OK);
			}catch(BookNotFoundException ex)
			{
				return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
				
			}
	}
	
	

}
