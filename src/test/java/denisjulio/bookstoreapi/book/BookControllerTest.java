package denisjulio.bookstoreapi.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class BookControllerTest {

  @Container
  @ServiceConnection
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

  private JSONArray booksJson;

  @Autowired
  private MockMvc mvc;
  @Autowired
  private ObjectMapper mapper;

  @BeforeEach
  void setup() throws IOException, JSONException {
    var resource = new ClassPathResource("json/books.json");
    var jsonNode = mapper.readTree(resource.getInputStream());
    booksJson = new JSONArray(jsonNode.toString());
  }

  @Test
  void whenGetBooksThenReturnAListOfBooks() throws Exception {
    var res = mvc.perform(get("/books"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(5))
            .andReturn().getResponse().getContentAsString();
    var lastBookJson = (JSONObject) booksJson.get(4);
    var lastBookResJson = (JSONObject) new JSONArray(res).get(4);
    JSONAssert.assertEquals(lastBookJson, lastBookResJson, JSONCompareMode.STRICT);
  }

  @Test
  void whenGetBooksFilteredByGenreThenReturnAListOfFilteredBooks() throws Exception {
    var res = mvc.perform(get("/books").queryParam("genre", "Genre 1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(3))
            .andReturn().getResponse().getContentAsString();
    var lastBookJson = (JSONObject) booksJson.get(4);
    var lastBookResJson = (JSONObject) new JSONArray(res).get(2);
    JSONAssert.assertEquals(lastBookJson, lastBookResJson, JSONCompareMode.STRICT);
  }
}