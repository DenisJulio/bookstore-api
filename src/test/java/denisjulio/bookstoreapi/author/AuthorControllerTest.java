package denisjulio.bookstoreapi.author;

import jakarta.annotation.Resource;
import org.json.JSONArray;
import org.json.JSONObject;
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
}