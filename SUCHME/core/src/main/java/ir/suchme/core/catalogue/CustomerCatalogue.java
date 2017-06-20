package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.Customer;
import ir.suchme.core.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by mohammad on 6/21/17.
 */
@Component
public class CustomerCatalogue {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerCatalogue(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(String userName,String password,String name,String email){
        Customer user=new Customer(userName,password,name,email);
        user.setCreated(new Date());
        customerRepository.save(user);
    }
}
