package denisjulio.bookstoreapi.domain.service;

import denisjulio.bookstoreapi.common.AbstractIntegrationTest;
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

  @DynamicPropertySource
  static void overrideProperties(DynamicPropertyRegistry registry) {
    overridePropertiesInternal(registry);
  }

  @Test
  public void whenGetAuthorsThenReturnCorrectAmount() {
    log.debug("test whenGetAuthorsThenReturnCorrectAmount");
    var authors = authorService.getAuthors();
    assertThat(authors).isNotEmpty();
    assertThat(authors).hasSize(3);
  }

  @Test
  void whenGetAuthorByIdThenReturnCorrectAuthor() {
    log.debug("test whenGetAuthorByIdThenReturnCorrectAuthor");
    var author = authorService.getAuthorById(3);
    assertThat(author.isPresent()).isTrue();
    assertThat(author.get().getName()).isEqualTo("Author 3");
  }
}