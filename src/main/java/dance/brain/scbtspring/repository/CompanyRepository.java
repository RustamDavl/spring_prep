package dance.brain.scbtspring.repository;


import dance.brain.scbtspring.entity.Company;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends Repository<Company, Long> {
    Optional<Company> findById(Long id);


    void deleteById(Long id);

    @Query(name = "Company.findByIdIn")
    List<Company> findByIdIn(@Param("name like in NamedQuery") Long id1, Long id2);

    // optional, entity, future
    @Query("""
            select c from Company c
            join fetch c.locales
            where c.name = :companyName
            """)
    Optional<Company> findByName(@Param("companyName") String name);

    //list, stream
    List<Company> findAllByNameContainingIgnoreCase(String name);

}
