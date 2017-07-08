package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.SupplyComponent;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by Farzin on 7/8/2017.
 */
public interface SupplyComponentRepository extends CrudRepository<SupplyComponent, UUID>{
}
