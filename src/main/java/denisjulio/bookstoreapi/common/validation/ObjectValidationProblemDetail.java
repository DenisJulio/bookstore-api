package denisjulio.bookstoreapi.common.validation;

import org.springframework.http.ProblemDetail;

import java.util.ArrayList;
import java.util.List;

public class ObjectValidationProblemDetail extends ProblemDetail {

  private final List<ValidationError> validationErrors;

  private ObjectValidationProblemDetail(int statusCode) {
    super(statusCode);
    this.setTitle("Validation Error");
    this.setDetail("One or more fields in the request body failed validation.");
    this.validationErrors = new ArrayList<>();
  }

  public static ObjectValidationProblemDetail forStatus(int statusCode) {
    return new ObjectValidationProblemDetail(statusCode);
  }

  public List<ValidationError> getValidationErrors() {
    return validationErrors;
  }

  public void addValidationError(String field, String defaultMessage) {
    var valErr = new ValidationError(field, defaultMessage);
    this.validationErrors.add(valErr);
  }

  public record ValidationError(String field, String reason) {}
}
