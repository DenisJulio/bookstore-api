package denisjulio.bookstoreapi.domain.service;

import denisjulio.bookstoreapi.common.AbstractIntegrationTest;
import denisjulio.bookstoreapi.domain.entity.Author;
import denisjulio.bookstoreapi.domain.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class AuthorServiceTest extends AbstractIntegrationTest {

  @Autowired
  private AuthorService authorService;

  @Autowired
  private AuthorRepository authorRepository;

  @DynamicPropertySource
  static void overrideProperties(DynamicPropertyRegistry registry) {
    overridePropertiesInternal(registry);
  }

  @Test
  public void whenGetAuthorsThenReturnCorrectAmount() {
    log.debug("test whenGetAuthorsThenReturnCorrectAmount");
    var authorsList = authorService.getAuthors();
    assertThat(authorsList).isNotEmpty();
    assertThat(authorsList).hasSize(3);
  }

  @Test
  void whenGetAuthorByIdThenReturnCorrectAuthor() {
    log.debug("test whenGetAuthorByIdThenReturnCorrectAuthor");
    var authorsList = authorRepository.findAll();
    var lastAuthor = authorsList.get(authorsList.size() - 1);
    var authorOpt = authorService.getAuthorById(lastAuthor.getId());
    assertThat(authorOpt.isPresent()).isTrue();
    var author = authorOpt.get();
    assertThat(author).isEqualTo(lastAuthor);
    assertThat(author.getName()).isEqualTo("Author 3");
  }

  @Test
  void whenSaveAuthorThenItIsStoredInTheDatabase() {
    log.debug("test whenSaveAuthorThenItIsStoredInTheDatabase");
    var newAuthor = new Author("Author 4")
            .setCountryName("France");
    authorService.save(newAuthor);
    var authorsList = authorRepository.findAll();
    assertThat(authorsList).hasSize(4);
    var lastAuthor = authorsList.get(3).getName();
    assertThat(lastAuthor).isEqualTo("Author 4");
  }

  @Test
  void whenDeleteAuthorByIdThenAuthorIsRemovedFromDatabase() {
    log.debug("test whenDeleteAuthorByIdThenAuthorIsRemovedFromDatabase");
    var authorsList = authorRepository.findAll();
    var lastAuthor = authorsList.get(authorsList.size() - 1);
    authorService.deleteAuthorById(lastAuthor.getId());
    assertThat(authorRepository.findAll()).hasSize(authorsList.size() - 1);
  }
}