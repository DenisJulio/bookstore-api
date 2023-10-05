package denisjulio.bookstoreapi.book;

import denisjulio.bookstoreapi.author.Author;
import denisjulio.bookstoreapi.genre.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Book")
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id", nullable = false)
  private Integer id;

  @Size(max = 255)
  @NotNull
  @Column(name = "title", nullable = false)
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private Author author;

  @Column(name = "publication_date")
  private LocalDate publicationDate;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @Size(max = 255)
  @Column(name = "cover_image_url")
  private URL coverImageUrl;

  @Column(name = "number_of_pages")
  private Integer numberOfPages;


  @ManyToMany(cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
  })
  @JoinTable(
          name = "books_genres",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private Set<Genre> genres = new HashSet<>();

  public Book() {
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(LocalDate publicationDate) {
    this.publicationDate = publicationDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public URL getCoverImageUrl() {
    return coverImageUrl;
  }

  public void setCoverImageUrl(URL coverImageUrl) {
    this.coverImageUrl = coverImageUrl;
  }

  public Integer getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(Integer numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  public Set<Genre> getGenres() {
    return genres;
  }

  public void setGenres(Set<Genre> genres) {
    this.genres = genres;
  }

  public void addGenre(Genre genre) {
    this.genres.add(genre);
    genre.getBooks().add(this);
  }

  public void removeGenre(Genre genre) {
    this.genres.remove(genre);
    genre.getBooks().remove(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Book book)) return false;
    return this.id != null && Objects.equals(this.id, book.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}