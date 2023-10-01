package denisjulio.bookstoreapi.author;

import denisjulio.bookstoreapi.api.AuthorsApi;
import denisjulio.bookstoreapi.model.AuthorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController implements AuthorsApi {

  private final AuthorService authorService;
  private final AuthorMapper authorMapper;

  public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
    this.authorService = authorService;
    this.authorMapper = authorMapper;
  }

  @Override
  public ResponseEntity<List<AuthorDto>> getAuthors() {
    var authors = authorService.getAuthors();
    var authorsDto = authorMapper.authorsToAuthorsDto(authors);
    return ResponseEntity.ok(authorsDto);
  }

  @Override
  public ResponseEntity<AuthorDto> getAuthorById(Integer authorId) {
    return authorService.getAuthorById(authorId)
            .map(authorMapper::authorToAuthorDto)
            .map(ResponseEntity::ok)
            .orElseThrow(AuthorNotFoundException::new);
  }
}
