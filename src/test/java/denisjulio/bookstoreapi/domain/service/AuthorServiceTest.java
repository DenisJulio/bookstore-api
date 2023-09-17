package denisjulio.bookstoreapi.domain.service;

import denisjulio.bookstoreapi.common.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorServiceTest extends AbstractIntegrationTest {

  @Autowired
  private AuthorService authorService;

  @DynamicPropertySource
  static void overrideProperties(DynamicPropertyRegistry registry) {
    overridePropertiesInternal(registry);
  }

  @Test
  public void getAllAuthorsShouldGetCorrectAmountOfAuthors() {
    var authors = authorService.getAllAuthors();
    assertThat(authors).isNotEmpty();
    assertThat(authors).hasSize(3);
  }

  @Test
  void getAuthorByIdReturnCorrectAuthor() {
    var author = authorService.getAuthorById(3);
    assertThat(author.isPresent()).isTrue();
    assertThat(author.get().getName()).isEqualTo("Author 3");
  }
}