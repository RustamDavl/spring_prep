package dance.brain.scbtspring.repository;

import dance.brain.scbtspring.database.ConnectionPool;

public class UserRepository {

    private final ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static UserRepository of(ConnectionPool connectionPool) {
        System.out.println("From static of(ConnectionPool) method");
        return new UserRepository(connectionPool);
    }

    @Override
    public String toString() {

        return "From user repository " + connectionPool.toString();
    }
}
