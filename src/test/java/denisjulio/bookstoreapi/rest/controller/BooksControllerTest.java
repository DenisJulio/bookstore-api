package denisjulio.bookstoreapi.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Test
    public void getAllBooksMustReturnEmpty() throws Exception {
        this.mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void getBooksByGenreShouldReturnFilteredBooks() throws Exception {
        String genre = "science fiction";

        this.mockMvc.perform(get("/books").param("genre", genre))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].genre[0]").value(genre)); // Assuming the first book's genre is "science fiction"
    }

    @Test
    public void getBookByIdShouldReturnCorrectBook() throws Exception {
        // Assuming there is a book with ID 25 in the database
        int bookId = 25;

        this.mockMvc.perform(get("/books/{bookId}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").exists()); // Add more assertions as needed
    }


    @Test
    public void getBookByInvalidIdShouldReturn404() throws Exception {
        int invalidBookId = 999; // Assuming there's no book with ID 999

        this.mockMvc.perform(get("/books/{bookId}", invalidBookId))
                .andExpect(status().isNotFound());
    }
}