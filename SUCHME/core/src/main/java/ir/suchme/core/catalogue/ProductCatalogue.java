package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mohammad on 6/18/17.
 */
@Component
public class ProductCatalogue {
    private final ProductRepository productRepository;

    @Autowired
    public ProductCatalogue(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> search(String name){
        return productRepository.findAllByNameLike(name);
    }

    public List<Product> findSimilarProducts(Product product)
    {
        return null;
//        for(ir.suchme.core.domain.entity.Component component : product.get)
    }
}
