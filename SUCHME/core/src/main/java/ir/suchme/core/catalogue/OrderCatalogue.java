package ir.suchme.core.catalogue;

import ir.suchme.core.domain.ProductOrder;
import ir.suchme.core.domain.entity.*;
import ir.suchme.core.domain.repository.ComponentOrderRepository;
import ir.suchme.core.domain.repository.OrderRepository;
import ir.suchme.core.domain.repository.ProductOrderRepository;
import ir.suchme.core.domain.repository.SupplyComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mohammad on 6/25/17.
 */
@Component
public class OrderCatalogue {


    private final ComponentOrderRepository componentOrderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final OrderRepository orderRepository;
    private final SupplyComponentRepository supplyComponentRepository;

    @Autowired
    public OrderCatalogue(ComponentOrderRepository componentOrderRepository, ProductOrderRepository productOrderRepository, OrderRepository orderRepository, SupplyComponentRepository supplyComponentRepository) {
        this.componentOrderRepository = componentOrderRepository;
        this.productOrderRepository = productOrderRepository;
        this.orderRepository = orderRepository;
        this.supplyComponentRepository = supplyComponentRepository;
    }


    public void orderComponent(ir.suchme.core.domain.entity.Component component, Supplier supplier,Integer quantity,Integer price){
        ComponentOrder componentOrder=new ComponentOrder(component,supplier,new Date(),quantity,price);
        componentOrderRepository.save(componentOrder);
    }
    public void orderProduct(Product product,Integer quantity){
        ProductOrder productOrder=new ProductOrder(product,new Date(),quantity);
        productOrderRepository.save(productOrder);
    }


    public List<ComponentOrder> listComponentOrders(Date from, Date to, Pageable pageable){
        return componentOrderRepository.findAllByCreatedBetweenAndDeletedIsFalse(from,to,pageable);
    }
    public List<ProductOrder> listProductOrders(Date from, Date to, Pageable pageable){
        return productOrderRepository.findAllByCreatedBetweenAndDeletedIsFalse(from,to,pageable);
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

    public Order findOne(String id){
        return orderRepository.findOne(UUID.fromString(id));
    }
    public void confirm(Order order){
        if(order instanceof ProductOrder)
            System.out.println("PROD");
        else if (order instanceof ComponentOrder) {
            ComponentOrder componentOrder=(ComponentOrder)order;
            SupplyComponent supplyComponent=supplyComponentRepository.findByComponentAndSupplier(componentOrder.getComponent(),componentOrder.getSupplier());
            if(supplyComponent==null)
                supplyComponent=new SupplyComponent(componentOrder.getPrice(),null,componentOrder.getQuantity(),componentOrder.getSupplier(),componentOrder.getComponent());
            else
                supplyComponent.setQuantity(supplyComponent.getQuantity()+componentOrder.getQuantity());
            supplyComponentRepository.save(supplyComponent);
            order.setDeleted(true);
            orderRepository.save(order);
        }
        else
            throw new AssertionError("Order type undefined");
    }
}
