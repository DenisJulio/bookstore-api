package denisjulio.bookstoreapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class TestConfig {

  @Bean(name = "authorsJson")
  public JSONArray authorsJson(ObjectMapper mapper) throws IOException, JSONException {
    var resource = new ClassPathResource("json/authors.json");
    var jsonNode = mapper.readTree(resource.getInputStream());
    return new JSONArray(jsonNode.toString());
  }
}
