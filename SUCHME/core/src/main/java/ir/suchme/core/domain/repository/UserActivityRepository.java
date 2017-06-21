package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.UserActivity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mohammad on 2017-06-13.
 */
public interface UserActivityRepository extends PagingAndSortingRepository<UserActivity,UUID> {
    @Query("select u from UserActivity u, Customer c where u.user.id = c.id " +
            "and u.created>?1 and u.created<?2 order by u.created")
    List<UserActivity> findAllCustomers(Date from, Date to,Pageable pageable);

    @Query("select u from UserActivity u, Employee e where u.user.id = e.id " +
            "and u.created>?1 and u.created<?2 order by u.created")
    List<UserActivity> findAllEmployees(Date from, Date to,Pageable pageable);

    List<UserActivity> findAllByCreatedBetween(Date from, Date to,Pageable pageable);

    @Query("select count(u.id) from UserActivity u, Customer c where u.user.id = c.id " +
            "and u.created>?1 and u.created<?2")
    Long countAllCustomersByCreatedBetween(Date from, Date to);


    @Query("select count(u.id) from UserActivity u, Employee e where u.user.id = e.id " +
            "and u.created>?1 and u.created<?2")
    Long countAllEmployeesByCreatedBetween(Date from, Date to);

    Long countAllByCreatedBetween(Date from,Date to);
}
