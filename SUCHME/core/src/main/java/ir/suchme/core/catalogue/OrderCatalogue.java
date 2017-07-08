package ir.suchme.core.catalogue;

import ir.suchme.core.domain.ProductOrder;
import ir.suchme.core.domain.entity.ComponentOrder;
import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.repository.ComponentOrderRepository;
import ir.suchme.core.domain.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by mohammad on 6/25/17.
 */
@Component
public class OrderCatalogue {


    private final ComponentOrderRepository componentOrderRepository;
    private final ProductOrderRepository productOrderRepository;

    @Autowired
    public OrderCatalogue(ComponentOrderRepository componentOrderRepository, ProductOrderRepository productOrderRepository) {
        this.componentOrderRepository = componentOrderRepository;
        this.productOrderRepository = productOrderRepository;
    }


    public void orderComponent(ir.suchme.core.domain.entity.Component component, Supplier supplier,Integer quantity){
        ComponentOrder componentOrder=new ComponentOrder(component,supplier,new Date(),quantity);
        componentOrderRepository.save(componentOrder);
    }

    public List<ComponentOrder> listComponentOrders(Date from, Date to, Pageable pageable){
        return componentOrderRepository.findAllByCreatedBetween(from,to,pageable);
    }
    public List<ProductOrder> listProductOrders(Date from, Date to, Pageable pageable){
        return productOrderRepository.findAllByCreatedBetween(from,to,pageable);
    }

    public Integer totalPages(Date from,Date to,Integer size,boolean product,boolean component){
        Integer count=0;
        if(product&&component)
            count= productOrderRepository.countAllByCreatedBetween(from, to) + componentOrderRepository.countAllByCreatedBetween(from, to);
        else if (product)
            count=productOrderRepository.countAllByCreatedBetween(from, to);
        else if(component)
            count=componentOrderRepository.countAllByCreatedBetween(from, to);
        return count==0?1:count % size == 0 ? count / size : (count/size)+1;
    }
}
