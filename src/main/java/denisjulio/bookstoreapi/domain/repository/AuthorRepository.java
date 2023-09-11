package denisjulio.bookstoreapi.domain.repository;

import denisjulio.bookstoreapi.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}