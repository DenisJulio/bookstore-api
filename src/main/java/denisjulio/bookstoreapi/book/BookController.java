package denisjulio.bookstoreapi.book;

import denisjulio.bookstoreapi.api.BooksApi;
import denisjulio.bookstoreapi.model.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
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
    if (genre != null) {
      log.debug("query string 'genre': {}", genre);
      if (genre.isBlank())
        throw new GenreParamNotAllowedException();
      var books = booksRepository.findBooksByGenresName(genre);
      var booksDto = bookMapper.toBooksDto(books);
      return ResponseEntity.ok(booksDto);
    }
    var books = booksRepository.findAll();
    var booksDto = bookMapper.toBooksDto(books);
    return ResponseEntity.ok(booksDto);
  }
}
