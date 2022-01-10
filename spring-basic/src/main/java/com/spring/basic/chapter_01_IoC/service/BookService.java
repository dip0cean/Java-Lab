package com.spring.basic.chapter_01_IoC.service;

import com.spring.basic.chapter_01_IoC.domain.Book;
import com.spring.basic.chapter_01_IoC.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class BookService {

    private BookRepository bookRepository;

    /**
     * BookService 객체가 런타임 시 생성되면서 Spring IoC Container 에 의해
     * Repository Bean 객체인 BookRepository 를 주입한다.
     *
     * @param bookRepository 런타임 시 BookRepository 주입
     */
//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    @Autowired
    public void setBookRepository (BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println();
        log.info("@Repository :: BookRepository 인스턴스를 주입 받은 뒤 실행되는 메소드입니다. {}", bookRepository.toString());
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
