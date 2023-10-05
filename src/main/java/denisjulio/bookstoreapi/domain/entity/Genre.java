package denisjulio.bookstoreapi.domain.entity;

import denisjulio.bookstoreapi.book.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
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