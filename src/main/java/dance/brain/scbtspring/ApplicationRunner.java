package dance.brain.scbtspring;

import dance.brain.scbtspring.config.ApplicationConfiguration;
import dance.brain.scbtspring.database.ConnectionPool;
import dance.brain.scbtspring.repository.CompanyRepository;
import dance.brain.scbtspring.repository.CrudRepository;
import dance.brain.scbtspring.repository.UserRepository;
import dance.brain.scbtspring.service.CompanyService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext classPathXmlApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        CrudRepository bean = classPathXmlApplicationContext.getBean(CrudRepository.class);
        CompanyService bean1 = classPathXmlApplicationContext.getBean(CompanyService.class);
        System.out.println(bean1.getCompanyRepository());

        classPathXmlApplicationContext.close();


    }
}
