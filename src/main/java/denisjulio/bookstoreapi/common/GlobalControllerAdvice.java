package denisjulio.bookstoreapi.common;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler
  ResponseEntity<ProblemDetail> handleBadRequest(Exception ex) {
    var problem = ProblemDetail.forStatus(400);
    problem.setTitle("Bad Request");
    problem.setDetail("The request could not be understood or was missing required parameters.");
    return ResponseEntity.of(problem).build();
  }
}
