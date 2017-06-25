package ir.suchme.core.catalogue;

import ir.suchme.core.domain.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by mohammad on 6/9/17.
 */

@Component
public class ComponentCatalogue {
    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentCatalogue(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
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
}
