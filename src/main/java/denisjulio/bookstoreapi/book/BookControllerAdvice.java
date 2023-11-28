package denisjulio.bookstoreapi.book;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "denisjulio.bookstoreapi.book")
public class BookControllerAdvice {

  @ExceptionHandler
  ResponseEntity<ProblemDetail> handleBookNotFound(BookNotFoundException ex) {
    var problem = ProblemDetail.forStatus(404);
    problem.setTitle("Book not found");
    problem.setDetail(ex.getMessage());
    return ResponseEntity.of(problem).build();
  }
}
