package dance.brain.scbtspring.integration.annotation;

import dance.brain.scbtspring.integration.TestApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationRunner.class)
@Transactional
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IT {
}
