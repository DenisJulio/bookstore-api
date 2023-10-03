package denisjulio.bookstoreapi.author;

import denisjulio.bookstoreapi.common.validation.ObjectValidationProblemDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

import static java.util.Objects.requireNonNull;

@Slf4j
@ControllerAdvice("denisjulio.bookstoreapi.author")
public class AuthorControllerAdvice {

  private final MessageSource msg;

  public AuthorControllerAdvice(MessageSource messageSource) {
    this.msg = messageSource;
  }

  @ExceptionHandler
  ResponseEntity<ProblemDetail> handleAuthorNotFound(AuthorNotFoundException ex) {
    var problem = ProblemDetail.forStatus(404);
    problem.setTitle(ex.getTitle());
    problem.setDetail(ex.getMessage());
    return ResponseEntity.of(problem).build();
  }

  @ExceptionHandler
  ResponseEntity<ProblemDetail> handleAuthorSubmissionDataNotValid(MethodArgumentNotValidException ex) {
    var problem = ObjectValidationProblemDetail.forStatus(422);
    ex.getFieldErrors()
            .forEach(fe -> {
              var reason = msg.getMessage(requireNonNull(fe.getDefaultMessage()), null, Locale.getDefault());
              problem.addValidationError(fe.getField(), reason);
            });
    return ResponseEntity.of(problem).build();
  }
}
