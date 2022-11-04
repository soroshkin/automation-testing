package automation;

import com.epam.microservices.automation.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

  protected static final String GATEWAY_URL = "http://localhost:8099";

  @Autowired
  protected TestRestTemplate testRestTemplate;
}