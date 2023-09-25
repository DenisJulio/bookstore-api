package denisjulio.bookstoreapi.author;

import denisjulio.bookstoreapi.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}