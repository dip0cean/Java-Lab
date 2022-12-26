package com.study.kotlin.service.book;

import com.study.kotlin.domain.book.Book;
import com.study.kotlin.domain.book.BookRepository;
import com.study.kotlin.domain.user.User;
import com.study.kotlin.domain.user.UserRepository;
import com.study.kotlin.domain.user.loanhistory.UserLoanHistoryRepository;
import com.study.kotlin.dto.book.request.BookLoanRequest;
import com.study.kotlin.dto.book.request.BookRequest;
import com.study.kotlin.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final UserRepository userRepository;
  private final UserLoanHistoryRepository userLoanHistoryRepository;

  public BookService(
      BookRepository bookRepository,
      UserRepository userRepository,
      UserLoanHistoryRepository userLoanHistoryRepository
  ) {
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
    this.userLoanHistoryRepository = userLoanHistoryRepository;
  }

  @Transactional
  public void saveBook(BookRequest request) {
    Book newBook = new Book(request.getName());
    bookRepository.save(newBook);
  }

  @Transactional
  public void loanBook(BookLoanRequest request) {
    Book book = bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
    if (userLoanHistoryRepository.findByBookNameAndIsReturn(request.getBookName(), false) != null) {
      throw new IllegalArgumentException("진작 대출되어 있는 책입니다");
    }

    User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
    user.loanBook(book);
  }

  @Transactional
  public void returnBook(BookReturnRequest request) {
    User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
    user.returnBook(request.getBookName());
  }

}
