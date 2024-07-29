package dance.brain.scbtspring;

import dance.brain.scbtspring.database.ConnectionPool;
import dance.brain.scbtspring.repository.UserRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("context.xml");
        UserRepository bean = classPathXmlApplicationContext.getBean(UserRepository.class);
        System.out.println(bean);

    }
}
