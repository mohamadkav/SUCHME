package ir.suchme.core.domain.repository;

import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.entity.enums.ProductState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by mohammad on 6/18/17.
 */
public interface ProductRepository extends CrudRepository<Product,UUID> {
    List<Product> findAllByNameLike(String name);

    List<Product> findAllByNameAndProductState(String name, ProductState state);
}
