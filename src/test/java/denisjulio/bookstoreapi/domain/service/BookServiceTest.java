package denisjulio.bookstoreapi.domain.service;

import denisjulio.bookstoreapi.common.AbstractIntegrationTest;
import denisjulio.bookstoreapi.domain.entity.Book;
import denisjulio.bookstoreapi.domain.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest extends AbstractIntegrationTest {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  BookService bookService;

  @Test
  void queryDb() {
    List<Book> books = bookService.findAll();
    assertThat(books).isEmpty();
  }
}
