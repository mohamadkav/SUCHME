package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by mohammad on 5/22/17.
 */
public interface UserRepository extends CrudRepository<User,UUID> {
    User findByUserNameAndDeletedIsFalse(String userName);
//    List<User> findAll();
}
