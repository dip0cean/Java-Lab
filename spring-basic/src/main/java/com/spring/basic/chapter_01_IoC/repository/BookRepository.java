package com.spring.basic.chapter_01_IoC.repository;

import com.spring.basic.chapter_01_IoC.domain.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public Book save(Book book) {
        return book;
    }

    public Book findById(String id) {
        return null;
    }
}
