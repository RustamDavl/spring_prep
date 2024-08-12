package dance.brain.scbtspring.integration;

import dance.brain.scbtspring.database.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestApplicationRunner {

    @MockBean(name = "pool1")
    private ConnectionPool connectionPool;
}
