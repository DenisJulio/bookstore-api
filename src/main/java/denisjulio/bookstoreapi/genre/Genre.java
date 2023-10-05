package denisjulio.bookstoreapi.genre;

import denisjulio.bookstoreapi.book.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "genre_id", nullable = false)
  private Integer id;

  @Size(max = 255)
  @NotBlank
  @Column(name = "name", nullable = false, unique = true)
  @NaturalId
  private String name;

  @ManyToMany(mappedBy = "genres")
  private Set<Book> books = new LinkedHashSet<>();

  public Genre() {
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Genre genre)) return false;
    return this.name != null && Objects.equals(this.name, genre.getName());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}