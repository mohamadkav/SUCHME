package ir.suchme.core.catalogue;

import ir.suchme.core.domain.ProductOrder;
import ir.suchme.core.domain.entity.Process;
import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.entity.Requirement;
import ir.suchme.core.domain.entity.SupplyComponent;
import ir.suchme.core.domain.entity.enums.ProductState;
import ir.suchme.core.domain.entity.enums.ProductType;
import ir.suchme.core.domain.repository.ProductOrderRepository;
import ir.suchme.core.domain.repository.ProductRepository;
import ir.suchme.core.domain.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by mohammad on 6/18/17.
 */
@Component
public class ProductCatalogue {
    private final ProductRepository productRepository;

    private final RequirementRepository requirementRepository;

    private final SupplyComponentCatalogue supplyComponentCatalogue;


    @Autowired
    public ProductCatalogue(ProductRepository productRepository, RequirementRepository requirementRepository, SupplyComponentCatalogue supplyComponentCatalogue) {
        this.productRepository = productRepository;
        this.requirementRepository = requirementRepository;
        this.supplyComponentCatalogue = supplyComponentCatalogue;
    }

    public Iterable<Product> search(String name){
        return productRepository.findAllByNameLike(name);
    }

    public Product findById(String id)
    {
        return productRepository.findOne(UUID.fromString(id));
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

    public Set<Process> findProcess(Product product)
    {
//        Set<SupplyComponent> process = new HashSet<>();
//        if(product.getSubProducts() == null || product.getSubProducts().size() == 0)
//        {
//            process.addAll(product.getSupplyComponents());
//            return process;
//
//        }
//        else
//        {
//
//        }
        return null;
//
    }

    public Product createProductWithRequirements(String productName, List<String> requirements){
        Product product=new Product(null, ProductState.ORDERED,null,null,productName,0,null,null,null,null,null);
        productRepository.save(product);
        for(String requirement:requirements){
            Requirement requirement1=new Requirement(product,requirement);
            requirementRepository.save(requirement1);
        }
        return product;
    }

    public void createManufactureProcess(Product product, Set<String> supplyComponentsId, Set<String> productsId)
    {
        Set<SupplyComponent> supplyComponents = new HashSet<>();
        for (String supplyComponentId : supplyComponentsId)
        {
            SupplyComponent sc = supplyComponentCatalogue.findOne(supplyComponentId);
            if(sc != null)
                supplyComponents.add(sc);
        }
        Set<Product> subProducts = new HashSet<>();
        for (String productId : productsId)
        {
            Product p = findById(productId);
            if(p != null)
                subProducts.add(p);
        }
        product.setSupplyComponents(supplyComponents);
        product.setSubProducts(subProducts);
        productRepository.save(product);
    }

    public void createMiddlewareProduct(String name)
    {
        Product p = new Product();
        p.setProductType(ProductType.MIDDLEWARE);
        p.setName(name);
        productRepository.save(p);
    }

}
