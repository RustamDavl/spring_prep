package dance.brain.scbtspring.repository;

import dance.brain.scbtspring.annotation.Auditing;
import dance.brain.scbtspring.annotation.Transaction;
import dance.brain.scbtspring.database.ConnectionPool;
import dance.brain.scbtspring.entity.Company;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transaction
@Auditing
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool connectionPool;

    @Autowired
    public CompanyRepository(@Qualifier("pool1") ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method.");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method.");
    }

    @Override
    public String toString() {
        return "CompanyRepository with connection pool : " + connectionPool.toString();
    }

    @PostConstruct
    public void init() {
        System.out.println("init company repository");
    }
}
