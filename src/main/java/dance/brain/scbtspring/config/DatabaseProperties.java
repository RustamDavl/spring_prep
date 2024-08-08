package dance.brain.scbtspring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {
    private final String username;
    private final String password;
    private final String driver;
    private final String url;
    private final PoolProperties pool;
    private final List<PoolProperties> pools;
    private final String[] hosts;
    private final Map<String, Object> properties;

    @ConstructorBinding
    public DatabaseProperties(String username, String password,
                              String driver, String url,
                              PoolProperties pool, List<PoolProperties> pools,
                              String[] hosts, Map<String, Object> properties) {
        this.username = username;
        this.password = password;
        this.driver = driver;
        this.url = url;
        this.pool = pool;
        this.pools = pools;
        this.hosts = hosts;
        this.properties = properties;
    }

    @Getter
    public static class PoolProperties {
        private final Integer size;
        private final Integer timeout;

        public PoolProperties(Integer size, Integer timeout) {
            this.size = size;
            this.timeout = timeout;
        }
    }
}
