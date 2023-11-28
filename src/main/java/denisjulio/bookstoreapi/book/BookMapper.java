package denisjulio.bookstoreapi.book;

import denisjulio.bookstoreapi.author.Author;
import denisjulio.bookstoreapi.genre.Genre;
import denisjulio.bookstoreapi.model.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper
public interface BookMapper {

  BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

  @Mapping(target = "authorId", source = "author")
  @Mapping(target = "genre", source = "genres")
  BookDto toBookDto(Book book);

  List<BookDto> toBooksDto(List<Book> books);

  default List<String> mapGenres(Set<Genre> genres) {
    return genres.stream()
        .map(Genre::getName)
        .toList();
  }

  default int mapAuthor(Author author) {
    return author.getId();
  }
}
