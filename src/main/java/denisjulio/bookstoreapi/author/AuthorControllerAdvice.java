package denisjulio.bookstoreapi.author;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("denisjulio.bookstoreapi.author")
public class AuthorControllerAdvice {

  @ExceptionHandler
  ResponseEntity<ProblemDetail> handleAuthorNotFound(AuthorNotFoundException ex) {
    var problem = ProblemDetail.forStatus(404);
    problem.setTitle(ex.getTitle());
    problem.setDetail(ex.getMessage());
    return ResponseEntity.of(problem).build();
  }

  @ExceptionHandler
  ResponseEntity<ProblemDetail> handleBadRequest(Exception ex) {
    var problem = ProblemDetail.forStatus(400);
    problem.setTitle("Bad Request");
    problem.setDetail("The request is not properly formatted");
    return ResponseEntity.of(problem).build();
  }
}
