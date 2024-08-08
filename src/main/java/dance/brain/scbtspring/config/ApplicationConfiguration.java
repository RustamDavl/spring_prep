package dance.brain.scbtspring.config;

import dance.brain.scbtspring.database.ConnectionPool;
import dance.brain.scbtspring.repository.UserRepository;
import dance.brain.web.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.*;

@Import(WebConfiguration.class)
@Configuration
//@PropertySource("classpath:application.properties")
//@ComponentScan(basePackages = "dance.brain.scbtspring")
public class ApplicationConfiguration {

    @Bean
    public ConnectionPool pool2(@Value("${db.username}") String username,
                                @Value("${db.pool.size}") Integer poolSize) {
        return new ConnectionPool(username, poolSize);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("username-test", 100);
    }

    @Bean
    @Profile("prod")
    public UserRepository userRepository2() {
        return new UserRepository(pool3());
    }


}
