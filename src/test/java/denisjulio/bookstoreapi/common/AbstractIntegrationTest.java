package denisjulio.bookstoreapi.common;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
public class AbstractIntegrationTest {

  @Container
  @ServiceConnection
  protected static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

}
