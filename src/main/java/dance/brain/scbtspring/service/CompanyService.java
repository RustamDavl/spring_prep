package dance.brain.scbtspring.service;

import dance.brain.scbtspring.dto.CompanyReadDto;
import dance.brain.scbtspring.entity.Company;
import dance.brain.scbtspring.listener.entity.AccessType;
import dance.brain.scbtspring.listener.entity.EntityEvent;
import dance.brain.scbtspring.listener.entity.EntityPublisher;
import dance.brain.scbtspring.repository.CompanyRepository;
import dance.brain.scbtspring.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface CompanyService {

    Company getById(Long id);
}
