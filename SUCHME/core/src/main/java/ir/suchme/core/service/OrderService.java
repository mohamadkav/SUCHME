package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.order.RequestOrderComponentDTO;
import ir.suchme.core.catalogue.ComponentCatalogue;
import ir.suchme.core.catalogue.OrderCatalogue;
import ir.suchme.core.catalogue.SupplierCatalogue;
import ir.suchme.core.domain.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mohammad on 6/25/17.
 */

@Service
public class OrderService {

    private final ComponentCatalogue componentCatalogue;

    private final SupplierCatalogue supplierCatalogue;

    private final OrderCatalogue orderCatalogue;

    @Autowired
    public OrderService(ComponentCatalogue componentCatalogue, SupplierCatalogue supplierCatalogue, OrderCatalogue orderCatalogue) {
        this.componentCatalogue = componentCatalogue;
        this.supplierCatalogue = supplierCatalogue;
        this.orderCatalogue = orderCatalogue;
    }

    public BaseResponseDTO orderComponent(RequestOrderComponentDTO request){
        if(request.getComponentName()==null) {
            if (componentCatalogue.findOne(request.getComponentId()) == null)
                return new BaseResponseDTO("Component not found", "-100", null);
            orderCatalogue.orderComponent(componentCatalogue.findOne(request.getComponentId()), request.getQuantity());
            return new BaseResponseDTO(null, "0", null);
        }
        else if(request.getSupplierId()!=null){
            if (supplierCatalogue.findOne(request.getSupplierId()) == null)
                return new BaseResponseDTO("Supplier not found", "-100", null);
            orderCatalogue.orderNewComponent(request.getComponentName(),request.getPrice(),request.getQuantity(),
                    supplierCatalogue.findOne(request.getSupplierId()));
            return new BaseResponseDTO(null, "0", null);
        }
        else if(request.getSupplierName()!=null){
           Supplier supplier= supplierCatalogue.addSupplier(request.getSupplierName());
            orderCatalogue.orderNewComponent(request.getComponentName(),request.getPrice(),request.getQuantity(), supplier);
            return new BaseResponseDTO(null, "0", null);
        }
        else
            return new BaseResponseDTO("Invalid Operation", "-100", null);
    }
}
