package ir.suchme.core.catalogue;

import ir.suchme.core.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mohammad on 6/21/17.
 */
@Component
public class EmployeeCatalogue {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeCatalogue(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
