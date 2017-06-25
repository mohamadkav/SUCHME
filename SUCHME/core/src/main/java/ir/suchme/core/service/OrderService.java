package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.order.RequestOrderComponentDTO;
import ir.suchme.core.catalogue.ComponentCatalogue;
import ir.suchme.core.catalogue.OrderCatalogue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mohammad on 6/25/17.
 */

@Service
public class OrderService {

    private final ComponentCatalogue componentCatalogue;

    private final OrderCatalogue orderCatalogue;

    @Autowired
    public OrderService(ComponentCatalogue componentCatalogue, OrderCatalogue orderCatalogue) {
        this.componentCatalogue = componentCatalogue;
        this.orderCatalogue = orderCatalogue;
    }

    public BaseResponseDTO orderComponent(RequestOrderComponentDTO request){
        if(componentCatalogue.findOne(request.getComponentId())==null)
            return new BaseResponseDTO("Component not found","-100",null);
        orderCatalogue.orderComponent(componentCatalogue.findOne(request.getComponentId()),request.getQuantity());
        return new BaseResponseDTO(null,"0",null);
    }
}
