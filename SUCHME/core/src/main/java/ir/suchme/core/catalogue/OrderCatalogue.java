package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.ComponentOrder;
import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.repository.ComponentOrderRepository;
import ir.suchme.core.domain.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by mohammad on 6/25/17.
 */
@Component
public class OrderCatalogue {


    private final ComponentOrderRepository componentOrderRepository;

    private final ComponentRepository componentRepository;

    @Autowired
    public OrderCatalogue(ComponentOrderRepository componentOrderRepository, ComponentRepository componentRepository) {
        this.componentOrderRepository = componentOrderRepository;
        this.componentRepository = componentRepository;
    }


    public void orderComponent(ir.suchme.core.domain.entity.Component component,Integer quantity){
        ComponentOrder componentOrder=new ComponentOrder(component,new Date(),quantity);
        componentOrderRepository.save(componentOrder);
    }
    public void orderNewComponent(String componentName,Integer price,Integer quantity, Supplier supplier){
        ir.suchme.core.domain.entity.Component component=new ir.suchme.core.domain.entity.Component(
                componentName,price,null,null,supplier);
        componentRepository.save(component);
        ComponentOrder componentOrder=new ComponentOrder(component,new Date(),quantity);
        componentOrderRepository.save(componentOrder);
    }
}
