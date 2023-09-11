package denisjulio.bookstoreapi.domain.service;

import denisjulio.bookstoreapi.domain.entity.Book;
import denisjulio.bookstoreapi.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }
}
