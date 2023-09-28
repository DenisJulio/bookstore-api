package denisjulio.bookstoreapi.author;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@Testcontainers
@Import(AuthorService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorServiceTest {

  @Container
  @ServiceConnection
  private final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

  @Autowired
  private AuthorService authorService;

  @Autowired
  private AuthorRepository authorRepository;

  @Test
  public void whenGetAuthorsThenReturnCorrectAmount() {
    log.debug("test whenGetAuthorsThenReturnCorrectAmount");
    var authorsList = authorService.getAuthors();
    assertThat(authorsList).hasSize(3);
  }

  @Test
  void whenGetAuthorByIdThenReturnCorrectAuthor() {
    log.debug("test whenGetAuthorByIdThenReturnCorrectAuthor");
    // given
    var authorsList = authorRepository.findAll();
    var lastAuthor = authorsList.get(authorsList.size() - 1);
    // when
    var authorOpt = authorService.getAuthorById(lastAuthor.getId());
    // then
    assertThat(authorOpt).isPresent();
    var author = authorOpt.get();
    assertThat(author).isEqualTo(lastAuthor);
    assertThat(author.getName()).isEqualTo("Author 3");
  }

  @Test
  void whenSaveAuthorThenItIsStoredInTheDatabase() {
    log.debug("test whenSaveAuthorThenItIsStoredInTheDatabase");
    // given
    var newAuthor = new Author("Author 4")
            .setCountryName("France");
    // when
    authorService.save(newAuthor);
    // then
    var authorsList = authorRepository.findAll();
    assertThat(authorsList).hasSize(4);
    var lastAuthorName = authorsList.get(authorsList.size() - 1).getName();
    assertThat(lastAuthorName).isEqualTo("Author 4");
  }

  @Test
  void whenDeleteAuthorByIdThenAuthorIsRemovedFromDatabase() {
    log.debug("test whenDeleteAuthorByIdThenAuthorIsRemovedFromDatabase");
    // given
    var authorsList = authorRepository.findAll();
    var lastAuthor = authorsList.get(authorsList.size() - 1);
    // when
    authorService.deleteAuthorById(lastAuthor.getId());
    // then
    assertThat(authorRepository.findAll()).hasSize(authorsList.size() - 1);
  }
}