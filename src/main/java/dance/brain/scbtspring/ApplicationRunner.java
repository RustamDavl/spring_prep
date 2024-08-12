package dance.brain.scbtspring;

import dance.brain.scbtspring.config.ApplicationConfiguration;
import dance.brain.scbtspring.database.ConnectionPool;
import dance.brain.scbtspring.repository.CompanyRepository;
import dance.brain.scbtspring.repository.CrudRepository;
import dance.brain.scbtspring.repository.UserRepository;
import dance.brain.scbtspring.service.CompanyService;
import dance.brain.web.WebConfiguration;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println();
    }
}
