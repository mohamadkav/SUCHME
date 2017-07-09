package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.ComponentOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mohammad on 6/25/17.
 */
public interface ComponentOrderRepository extends CrudRepository<ComponentOrder,UUID> {
    List<ComponentOrder>findAllByCreatedBetweenAndDeletedIsFalse(Date from, Date to, Pageable pageable);
    Integer countAllByCreatedBetween(Date from, Date to);
}
