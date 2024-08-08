package dance.brain.scbtspring.database;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pool1")
@Slf4j
public class ConnectionPool {

    private final String username;
    private final Integer poolSize;
//    private final List<Object> args;
//
//    private final Map<String, Object> properties;

    @Autowired
    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.pool.size}") Integer poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

//    public ConnectionPool(String username, Integer poolSize, List<Object> args, Map<String, Object> properties) {
//        this.username = username;
//        this.poolSize = poolSize;
//        this.args = args;
//        this.properties = properties;
//    }

    @PostConstruct
    public void init() {
        log.info("init connection pool with pool size : " + poolSize);
    }

    @PreDestroy
    public void destroy() {
        log.info("clean connection pool");
    }

    @Override
    public String toString() {
        return "ConnectionPool{" +
                "username='" + username + '\'' +
                ", poolSize=" + poolSize +
//                ", args=" + args +
//                ", properties=" + properties +
                '}';
    }
}
