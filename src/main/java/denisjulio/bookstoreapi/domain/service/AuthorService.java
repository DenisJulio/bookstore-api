package denisjulio.bookstoreapi.domain.service;

import denisjulio.bookstoreapi.domain.entity.Author;
import denisjulio.bookstoreapi.domain.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  public Optional<Author> getAuthorById(Integer id) {
    return authorRepository.findById(id);
  }

  public void save(Author newAuthor) {
    authorRepository.save(newAuthor);
  }
}
