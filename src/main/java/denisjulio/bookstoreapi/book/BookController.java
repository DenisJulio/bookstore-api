package denisjulio.bookstoreapi.book;

import denisjulio.bookstoreapi.api.BooksApi;
import denisjulio.bookstoreapi.model.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BooksApi {

  private final BookRepository booksRepository;
  private final BookMapper bookMapper;

  public BookController(BookRepository booksRepository, BookMapper bookMapper) {
    this.booksRepository = booksRepository;
    this.bookMapper = bookMapper;
  }

  @Override
  public ResponseEntity<List<BookDto>> getBooks(String genre) {
    var books = booksRepository.findAll();
    var booksDto = bookMapper.toBooksDto(books);
    return ResponseEntity.ok(booksDto);
  }
}
