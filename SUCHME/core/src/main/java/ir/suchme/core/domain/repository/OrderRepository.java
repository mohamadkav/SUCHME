package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by mohammad on 7/8/17.
 */
public interface OrderRepository extends CrudRepository<Order,UUID>{
}
