package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<Product> findSimilarProducts(Product product)
    {
        HashSet<Product> out = new HashSet<>();
        for(Product p : productRepository.findAll())
        {
            if(p.isSimilarTo(product) && !p.equals(product))
                out.add(p);
        }
        return out;
    }
}
