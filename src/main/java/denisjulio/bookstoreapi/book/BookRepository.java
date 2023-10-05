package denisjulio.bookstoreapi.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

  List<Book> findBooksByGenresName(@Size(max = 255) @NotBlank String name);
}