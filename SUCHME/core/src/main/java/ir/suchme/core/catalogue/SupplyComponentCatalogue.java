package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.Component;
import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.entity.SupplyComponent;
import ir.suchme.core.domain.repository.SupplyComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Farzin on 7/9/2017.
 */
@org.springframework.stereotype.Component
public class SupplyComponentCatalogue {

    private final SupplyComponentRepository supplyComponentRepository;

    @Autowired

    public SupplyComponentCatalogue(SupplyComponentRepository supplyComponentRepository) {
        this.supplyComponentRepository = supplyComponentRepository;
    }

    public SupplyComponent findOne(String uuid)
    {
        return supplyComponentRepository.findOne(UUID.fromString(uuid));
    }

    public SupplyComponent findOneByComponentAndSupplierId(Component c, Supplier s){
        return supplyComponentRepository.findByComponentAndSupplier(c, s);
    }
    public HashMap<Component,List<Supplier>> getDifferentPathsForProduct(Product product){
        Set<SupplyComponent> supplyComponents=product.getSupplyComponents();
        HashMap<Component,List<Supplier>> toReturn=new HashMap<>();

    }
}
