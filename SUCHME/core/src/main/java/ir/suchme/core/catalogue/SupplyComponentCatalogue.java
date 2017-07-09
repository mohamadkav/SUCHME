package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.Component;
import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.entity.SupplyComponent;
import ir.suchme.core.domain.repository.ProductRepository;
import ir.suchme.core.domain.repository.SupplyComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Farzin on 7/9/2017.
 */
@org.springframework.stereotype.Component
public class SupplyComponentCatalogue {

    private final SupplyComponentRepository supplyComponentRepository;
    private final ProductRepository productRepository;

    @Autowired

    public SupplyComponentCatalogue(SupplyComponentRepository supplyComponentRepository, ProductRepository productRepository) {
        this.supplyComponentRepository = supplyComponentRepository;
        this.productRepository = productRepository;
    }

    public SupplyComponent findOne(String uuid)
    {
        return supplyComponentRepository.findOne(UUID.fromString(uuid));
    }

    public SupplyComponent findOneByComponentAndSupplierId(Component c, Supplier s){
        return supplyComponentRepository.findByComponentAndSupplier(c, s);
    }
    public HashMap<Component,List<SupplyComponent>> getDifferentPathsForProduct(Product product){
        Set<SupplyComponent> supplyComponents=product.getSupplyComponents();
        HashMap<Component,List<SupplyComponent>> toReturn=new HashMap<>();
        for(SupplyComponent supplyComponent:supplyComponents){
            toReturn.put(supplyComponent.getComponent(),new ArrayList<>());
            for(SupplyComponent componentSupplyComponents:supplyComponent.getComponent().getSupplyComponents())
                toReturn.get(supplyComponent.getComponent()).add(componentSupplyComponents);
        }
        return toReturn;
    }

    public HashMap<Component,SupplyComponent> getCurrentSupplyPath(Product product){
        HashMap<Component,SupplyComponent> toReturn=new HashMap<>();
        for(SupplyComponent supplyComponent:product.getSupplyComponents())
            toReturn.put(supplyComponent.getComponent(),supplyComponent);
        return toReturn;
    }

    public void changeSupplyPath(Product product,HashMap<Component,SupplyComponent> newPath){
        Set<SupplyComponent> supplyComponents=new HashSet<>();
        for(SupplyComponent supplyComponent :product.getSupplyComponents()){
            if(newPath.containsKey(supplyComponent.getComponent())&&
                    !newPath.get(supplyComponent.getComponent()).getSupplier().equals(supplyComponent.getSupplier())) {
                supplyComponents.add(newPath.get(supplyComponent.getComponent()));
                if(supplyComponent.getQuantity()!=null){
                    supplyComponent.setQuantity(supplyComponent.getQuantity()+1);
                    supplyComponentRepository.save(supplyComponent);
                }
            }
            else{
                supplyComponents.add(supplyComponent);
            }
        }
        product.setSupplyComponents(supplyComponents);
        productRepository.save(product);
    }
}
