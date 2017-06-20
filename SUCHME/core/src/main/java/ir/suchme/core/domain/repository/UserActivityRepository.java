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
/*    @Query("select u from UserActivity u join Customer.id " +
            "where UserActivity.created>?1 and UserActivity.created<?2 order by UserActivity.created")
    List<UserActivity> findAllByCreatedBetweenAndUserIsNotNullOrderByCreatedDesc(Date from, Date to,Pageable pageable);*/
}
