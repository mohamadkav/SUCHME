package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.UserActivity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by mohammad on 2017-06-13.
 */
public interface UserActivityRepository extends CrudRepository<UserActivity,UUID> {
}
