package denisjulio.bookstoreapi.author;

import denisjulio.bookstoreapi.domain.entity.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Author")
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Size(max = 255)
    @Column(name = "country_name")
    private String countryName;

    @Column(name = "biography", length = Integer.MAX_VALUE)
    private String biography;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Author setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public Author setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getBiography() {
        return biography;
    }

    public Author setBiography(String biography) {
        this.biography = biography;
        return this;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Author setBooks(Set<Book> books) {
        this.books = books;
        return this;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.setAuthor(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return this.id != null && Objects.equals(this.id, author.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}