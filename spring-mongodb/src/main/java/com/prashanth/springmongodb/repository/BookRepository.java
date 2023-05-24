package com.prashanth.springmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.prashanth.springmongodb.model.Book;

public interface BookRepository extends MongoRepository<Book,Integer>{

}
