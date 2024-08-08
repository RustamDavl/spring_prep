package dance.brain.scbtspring.config;

import dance.brain.scbtspring.config.condition.JpaCondition;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(JpaCondition.class)
@Configuration
@Slf4j
public class JpaConfiguration {

    @PostConstruct
    public void init() {
        log.info("Jpa configuration was enabled.");
    }
}
