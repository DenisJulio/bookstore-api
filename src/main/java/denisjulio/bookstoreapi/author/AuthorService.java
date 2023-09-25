package denisjulio.bookstoreapi.author;

import denisjulio.bookstoreapi.author.Author;
import denisjulio.bookstoreapi.author.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Author> getAuthors() {
    return authorRepository.findAll();
  }

  public Optional<Author> getAuthorById(Integer id) {
    return authorRepository.findById(id);
  }

  public void save(Author newAuthor) {
    authorRepository.save(newAuthor);
  }

  public void deleteAuthorById(Integer id) {
    authorRepository.deleteById(id);
  }
}