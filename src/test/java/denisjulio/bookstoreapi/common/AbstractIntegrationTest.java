package denisjulio.bookstoreapi.common;

import denisjulio.bookstoreapi.domain.entity.Author;
import denisjulio.bookstoreapi.domain.repository.AuthorRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public abstract class AbstractIntegrationTest {

  protected static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

  @Autowired
  private AuthorRepository authorRepository;

  @BeforeEach
  void baseSetUp() {
    authorRepository.deleteAll();
    authorRepository.saveAll(sampleAuthors());
  }

  private List<Author> sampleAuthors() {
    var authorOne = new Author("Author 1")
            .setBirthDate(LocalDate.of(1990, 5, 15))
            .setCountryName("Brazil")
            .setBiography("Author 1 biography");
    var authorTwo = new Author("Author 2")
            .setBirthDate(LocalDate.of(1985, 8, 20))
            .setCountryName("United States of America")
            .setBiography("Author 2 biography");
    var authorThree = new Author("Author 3")
            .setBirthDate(LocalDate.of(1978, 3, 10))
            .setCountryName("Argentina")
            .setBiography("Author 3 biography");
    return List.of(authorOne, authorTwo, authorThree);
  }

  @BeforeAll
  protected static void beforeAll() {
    postgres.start();
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }

  protected static void overridePropertiesInternal(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }
}
