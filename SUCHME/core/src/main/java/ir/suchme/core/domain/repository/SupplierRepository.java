package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by Farzin on 6/21/2017.
 */
public interface SupplierRepository extends CrudRepository<Supplier, UUID> {

    Supplier findFirstByName(String name);


}
