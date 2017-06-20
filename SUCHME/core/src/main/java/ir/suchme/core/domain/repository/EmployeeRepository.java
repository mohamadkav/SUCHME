package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by mohammad on 6/21/17.
 */
public interface EmployeeRepository extends CrudRepository<Employee,UUID>{
}
