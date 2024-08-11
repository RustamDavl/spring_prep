package dance.brain.scbtspring.integration.service;

import dance.brain.scbtspring.database.ConnectionPool;
import dance.brain.scbtspring.integration.annotation.IT;
import dance.brain.scbtspring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;

@IT
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool connectionPool;


    @Autowired
    public UserServiceIT(UserService userService, @Qualifier("pool1") ConnectionPool connectionPool) {
        this.userService = userService;
        this.connectionPool = connectionPool;
    }

    @Test
    void findById() {

    }
}
