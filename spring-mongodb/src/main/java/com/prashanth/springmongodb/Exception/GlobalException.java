package com.prashanth.springmongodb.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	   @ExceptionHandler(DuplicateBookException.class)
	    public ResponseEntity<String> handleDuplicateEmployeeException(DuplicateBookException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	    }
	   
	   @ExceptionHandler(BookNotFoundException.class)
	   public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
			   return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(BooksNotFoundException.class)
	   public ResponseEntity<String> handleBooksNotFoundException(BooksNotFoundException ex)
	   {
			  return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);		   
	   }	
	   
}
