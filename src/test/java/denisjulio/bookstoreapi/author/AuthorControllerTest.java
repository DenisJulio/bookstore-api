package denisjulio.bookstoreapi.author;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class AuthorControllerTest {

  @Container
  @ServiceConnection
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

  @Autowired
  private MockMvc mvc;

  @Resource(name = "authorsJson")
  private JSONArray authorsJson;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void whenGetAuthorsThenReturnListOfAuthors() throws Exception {
    var res = mvc.perform(get("/authors"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()", greaterThan(0)))
            .andExpect(jsonPath("$[0].id", not(blankOrNullString())))
            .andReturn()
            .getResponse()
            .getContentAsString();
    var firstAuthorJsonRes = (JSONObject) new JSONArray(res).get(0);
    var expectedAuthorJson = (JSONObject) authorsJson.get(0);
    JSONAssert.assertEquals(expectedAuthorJson, firstAuthorJsonRes, JSONCompareMode.LENIENT);
  }

  @Test
  void whenGetAuthorByIdThenReturnCorrectAuthor() throws Exception {
    var res = mvc.perform(get("/authors/{id}", 3))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", not(blankOrNullString())))
            .andReturn()
            .getResponse()
            .getContentAsString();
    var authorJsonRes = new JSONObject(res);
    var expectedAuthorJson = (JSONObject) authorsJson.get(2);
    JSONAssert.assertEquals(expectedAuthorJson, authorJsonRes, JSONCompareMode.STRICT);
  }

  @Test
  void whenGetAuthorByIdDoesntFindEntityThenReturnNotFound() throws Exception {
    var res = mvc.perform(get("/authors/{id}", 999))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andReturn().getResponse().getContentAsString();
    var json = """
            {
              title: "Author not found",
              detail: "Could not find an Author with the given 'id'"
            }
            """;
    var jsonRes = new JSONObject(res);
    JSONAssert.assertEquals(json, jsonRes, JSONCompareMode.LENIENT);
  }

  @Test
  @DisplayName("When getAuthorById with an invalid 'id' format, Then return Bad Request")
  void whenGetAuthorByIdThenReturnBadRequest() throws Exception {
    mvc.perform(get("/authors/{id}", "author1"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
  }

  //  TODO:
  // Given POST /authors
  //  When create a new author properly
  //  Then the response is as expected status 201
  //    and the database reflects the new state

  @Test
  @DisplayName("When postNewAuthor missing the request body, Then return Bad Request")
  void whenPostNewAuthorThenReturnBadRequest() throws Exception {
    var res = mvc.perform(post("/authors"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andReturn().getResponse().getContentAsString();
    var json = """
            {
              title: "Bad Request",
              status: 400,
              detail: "The request could not be understood or was missing required parameters."
            }
            """;
    var jsonRes = new JSONObject(res);
    JSONAssert.assertEquals(json, jsonRes, JSONCompareMode.LENIENT);
  }

  @Test
  @DisplayName("When postNewAuthor with invalid request body, Then return Validation Error")
  void whenPostNewAuthorThenReturnValidationError() throws Exception {
    var invalidAuthor = """
            {
              "birth_date": "22/12/1993"
            }
            """;
    var res = mvc.perform(post("/authors")
            .content(invalidAuthor)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.type").value(not(blankOrNullString())))
            .andExpect(jsonPath("$.instance").value(not(blankOrNullString())))
            .andExpect(jsonPath("$.validationErrors").isArray())
            .andExpect(jsonPath("$.validationErrors", hasSize(2)))
            .andReturn().getResponse().getContentAsString();
    var problemDetailJson = """
            {
              "title": "Validation Error",
              "status": 422,
              "detail": "One or more fields in the request body failed validation.",
              "validationErrors": [
                {
                  "field": "name",
                  "reason": "'name' is required, it can not be null or blank."
                },
                {
                  "field": "birthDate",
                  "reason": "'birthDate' must be in the format 'yyyy-mm-dd'."
                }
              ]
            }
            """;
    var jsonRes = new JSONObject(res);
    JSONAssert.assertEquals(problemDetailJson, jsonRes, JSONCompareMode.LENIENT);
  }
}