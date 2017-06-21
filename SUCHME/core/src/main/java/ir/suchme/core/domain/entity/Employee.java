package ir.suchme.core.domain.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by mohammad on 6/9/17.
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends User{


}
