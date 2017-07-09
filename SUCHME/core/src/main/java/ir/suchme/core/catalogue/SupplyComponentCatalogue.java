package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.entity.SupplyComponent;
import ir.suchme.core.domain.repository.SupplyComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Farzin on 7/9/2017.
 */
@Component
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

    public SupplyComponent findOneByComponentAndSupplierId(ir.suchme.core.domain.entity.Component c, Supplier s){
        return supplyComponentRepository.findByComponentAndSupplier(c, s);
    }
}
