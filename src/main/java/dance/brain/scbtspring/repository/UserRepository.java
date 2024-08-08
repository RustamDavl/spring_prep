package dance.brain.scbtspring.repository;

import dance.brain.scbtspring.database.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    private final ConnectionPool connectionPool;

    @Autowired
    public UserRepository(@Qualifier("pool1") ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public String toString() {
        return "From user repository " + connectionPool.toString();
    }
}
