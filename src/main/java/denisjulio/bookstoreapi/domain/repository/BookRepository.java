package denisjulio.bookstoreapi.domain.repository;

import denisjulio.bookstoreapi.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {


}