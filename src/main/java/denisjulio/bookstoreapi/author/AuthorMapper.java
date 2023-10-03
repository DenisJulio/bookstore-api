package denisjulio.bookstoreapi.author;

import denisjulio.bookstoreapi.model.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AuthorMapper {

  AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

  AuthorDto authorToAuthorDto(Author author);

  List<AuthorDto> authorsToAuthorsDto(List<Author> authors);

  @Mapping(target = "books", ignore = true)
  Author toAuthor(AuthorSubmissionData authorSubmissionData);
}
