package denisjulio.bookstoreapi.common.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import denisjulio.bookstoreapi.author.AuthorSubmissionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ValidationAutoConfiguration.class)
class ObjectValidationProblemDetailTest {

  @Autowired
  private Validator validator;
  private DataBinder binder;
  private ObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ObjectMapper();
    var authorData = new AuthorSubmissionData();
    authorData.setBirthDate("34/31/99");
    binder = new DataBinder(authorData);
    binder.setValidator(validator);
  }

  @Test
  void whenValidatingThenRejectObject() {
    binder.validate();
    var bindingRes = binder.getBindingResult();
    assertThat(bindingRes.getFieldErrors())
            .isNotEmpty()
            .hasSize(2);
  }

  @Test
  @DisplayName("Given the creation of an ObjectValidationProblemDetail, When validationErrors are added, " +
          "Then the serialized json conforms to expectation")
  void whenCreationIsCompletedThenJsonIsCorrect() throws Exception {
    // given
    var problem = ObjectValidationProblemDetail.forStatus(422);
    binder.validate();
    var bindingRes = binder.getBindingResult();
    // when
    bindingRes.getFieldErrors()
            .forEach(fieldError ->
                    problem.addValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
    // then
    var problemJsonCtx = JsonPath.parse(mapper.writeValueAsString(problem));
    assertThat(problemJsonCtx.read("$.type", String.class)).isNotBlank();
    assertThat(problemJsonCtx.read("$.title", String.class)).isNotBlank();
    assertThat(problemJsonCtx.read("$.detail", String.class)).isNotBlank();
    assertThat(problemJsonCtx.read("$.status", Integer.class)).isEqualTo(422);
    assertThat(problemJsonCtx.read("$.validationErrors", List.class))
            .isNotEmpty()
            .hasSize(2);
  }
}