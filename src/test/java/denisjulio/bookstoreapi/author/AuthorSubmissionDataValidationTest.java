package denisjulio.bookstoreapi.author;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorSubmissionDataValidationTest {


  private Validator validator;

  @BeforeAll
  void setup() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  void whenNoNameProvidedThenItsInvalid() {
    var authorData = new AuthorSubmissionData();
    var violations = validator.validate(authorData);
    assertThat(violations)
            .isNotEmpty()
            .hasSize(1);
  }

  @Test
  void whenNameProvidedThenItsValid() {
    var authorData = new AuthorSubmissionData("Author 1");
    var violations = validator.validate(authorData);
    assertThat(violations).isEmpty();
  }

  @Test
  void whenBirthDateIsProperThenItsValid() {
    var authorData = new AuthorSubmissionData("Author 1");
    authorData.setBirthDate("1992-05-28");
    var violations = validator.validate(authorData);
    assertThat(violations).isEmpty();
  }

  @ParameterizedTest
  @ValueSource(strings = {"2023-02-29", "1992-99-99", "28/05/1992"})
  void whenBirthDateIsNotProperThenItsInvalid(String birthDate) {
    var authorData = new AuthorSubmissionData("Author 1");
    authorData.setBirthDate(birthDate);
    var violations = validator.validate(authorData);
    assertThat(violations)
            .isNotEmpty()
            .hasSize(1);
  }
}