package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.entity.SupplyComponent;
import ir.suchme.core.domain.repository.ComponentRepository;
import ir.suchme.core.domain.repository.SupplierRepository;
import ir.suchme.core.domain.repository.SupplyComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by mohammad on 6/9/17.
 */

@Component
public class ComponentCatalogue {
    private final ComponentRepository componentRepository;
    private final SupplyComponentRepository supplyComponentRepository;

    @Autowired
    public ComponentCatalogue(ComponentRepository componentRepository, SupplyComponentRepository supplyComponentRepository) {
        this.componentRepository = componentRepository;
        this.supplyComponentRepository = supplyComponentRepository;
    }


    public Iterable<ir.suchme.core.domain.entity.Component> search(String name){
        if(name==null||name.trim().isEmpty())
            return componentRepository.findAll();
        return componentRepository.findAllByNameLike(name);
    }

    public ir.suchme.core.domain.entity.Component findOne(String uuid){
        return componentRepository.findOne(UUID.fromString(uuid));
    }

    public void updateMax(ir.suchme.core.domain.entity.Component component,Integer max){
        component.setMaxValue(max);
        componentRepository.save(component);
    }
    public void updateMin(ir.suchme.core.domain.entity.Component component,Integer min){
        component.setMinValue(min);
        componentRepository.save(component);
    }

    public ir.suchme.core.domain.entity.Component create(String name){
        ir.suchme.core.domain.entity.Component c = new ir.suchme.core.domain.entity.Component(name,null,null,null);
        componentRepository.save(c);
        return c;
    }
}
