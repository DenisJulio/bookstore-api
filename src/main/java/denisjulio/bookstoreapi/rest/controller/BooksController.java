package denisjulio.bookstoreapi.rest.controller;

import denisjulio.bookstoreapi.api.BooksApi;
import denisjulio.bookstoreapi.model.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class BooksController implements BooksApi {

    @Override
    public ResponseEntity<List<BookDto>> getBooks(String genre) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
