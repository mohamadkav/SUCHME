package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.order.*;
import ir.suchme.common.dto.process.RequestProductManufactureProcess;
import ir.suchme.common.dto.product.RequestCreateMiddlewareProduct;
import ir.suchme.core.catalogue.*;
import ir.suchme.core.domain.ProductOrder;
import ir.suchme.core.domain.entity.*;
import ir.suchme.core.domain.entity.enums.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mohammad on 6/25/17.
 */

@Service
public class OrderService {

    private final ComponentCatalogue componentCatalogue;

    private final SupplierCatalogue supplierCatalogue;

    private final OrderCatalogue orderCatalogue;

    private final ProductCatalogue productCatalogue;

    private final SupplyComponentCatalogue supplyComponentCatalogue;

    @Autowired
    public OrderService(ComponentCatalogue componentCatalogue, SupplierCatalogue supplierCatalogue, OrderCatalogue orderCatalogue, ProductCatalogue productCatalogue, SupplyComponentCatalogue supplyComponentCatalogue) {
        this.componentCatalogue = componentCatalogue;
        this.supplierCatalogue = supplierCatalogue;
        this.orderCatalogue = orderCatalogue;
        this.productCatalogue = productCatalogue;
        this.supplyComponentCatalogue = supplyComponentCatalogue;
    }

    public BaseResponseDTO orderComponent(RequestOrderComponentDTO request){
        if(request.getSupplierName()==null&&request.getSupplierId()==null)
            return new BaseResponseDTO("Select supplier", "-100", null);
        Component component;
        Supplier supplier;
        if(request.getSupplierId()!=null)
            supplier=supplierCatalogue.findOne(request.getSupplierId());
        else
            supplier=supplierCatalogue.addSupplier(request.getSupplierName());
        if(supplier==null)
            return new BaseResponseDTO("Supplier not found", "-100", null);


        if(request.getComponentName()==null) {
            if (componentCatalogue.findOne(request.getComponentId()) == null)
                return new BaseResponseDTO("Component not found", "-100", null);
            component=componentCatalogue.findOne(request.getComponentId());
        }
        else if(request.getSupplierId()!=null||request.getSupplierName()!=null){
            component=componentCatalogue.create(request.getComponentName());
        }
        else
            return new BaseResponseDTO("Invalid Operation", "-100", null);
        orderCatalogue.orderComponent(component,supplier, request.getQuantity(),request.getPrice());
        return new BaseResponseDTO(null, "0", null);
    }

    public BaseResponseDTO orderProduct(RequestOrderProductDTO request){
        Product p=productCatalogue.createProductWithRequirements(request.getProductName(),request.getRequirements());
        orderCatalogue.orderProduct(p,request.getQuantity());
        return new BaseResponseDTO(null, "0", null);
    }

    public ResponseOrderListDTO list(RequestOrderListDTO request){
        Pageable pageable = new PageRequest(request.getPage(), request.getSize());
        //Null values need specification. I don't have the time for that. TOF:
        Date from=request.getFromDate()==null?new Date(0L):new Date(request.getFromDate());
        Date to=request.getFromDate()==null?new Date():new Date(request.getToDate());

        List<OrderDTO> orderDTOS=new ArrayList<>();
        ResponseOrderListDTO res=new ResponseOrderListDTO();
        if(request.isProduct()) {
            List<ProductOrder> productOrders = orderCatalogue.listProductOrders(from, to, pageable);
            for (ProductOrder order : productOrders)
                orderDTOS.add(new OrderDTO(order.getProduct().getName(), order.getQuantity(), order.getCreated(), order.getId().toString()));
        }
        if(request.isComponent()) {
            List<ComponentOrder> componentOrders = orderCatalogue.listComponentOrders(from, to, pageable);
            for (ComponentOrder order : componentOrders)
                orderDTOS.add(new OrderDTO(order.getComponent().getName(), order.getQuantity(), order.getCreated(), order.getId().toString()));
        }
        res.setTotalPages(orderCatalogue.totalPages(from,to,request.getSize(), request.isProduct(),request.isComponent()));
        res.setOrderDTOS(orderDTOS);
        res.setResponseCode("0");
        return res;
    }
    public BaseResponseDTO confirm(RequestOrderConfirmDTO request){
        Order order=orderCatalogue.findOne(request.getId());
        if(order==null)
            return new BaseResponseDTO("Order not found","-100",null);
        orderCatalogue.confirm(order);
        return new BaseResponseDTO(null,"0",null);
    }

    public BaseResponseDTO createManufactureProductProcess(RequestProductManufactureProcess request)
    {
        Product parentProduct = productCatalogue.findById(request.getProductId());
        if(parentProduct == null)
            return new BaseResponseDTO("Product not found","-100",null);
        productCatalogue.createManufactureProcess(parentProduct, request.getComponentDTOS(), request.getProductsId());
        return new BaseResponseDTO(null, "0", null);
    }


}
