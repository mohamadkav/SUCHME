package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.RequestUpdateComponentMinMaxDTO;
import ir.suchme.core.catalogue.ComponentCatalogue;
import ir.suchme.core.domain.entity.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mohammad on 6/9/17.
 */

@Service
public class WarehouseService {

    private final ComponentCatalogue componentCatalogue;

    @Autowired
    public WarehouseService(ComponentCatalogue componentCatalogue) {
        this.componentCatalogue = componentCatalogue;
    }


    public BaseResponseDTO updateMinMax(RequestUpdateComponentMinMaxDTO request){
        Component component=componentCatalogue.findOne(request.getProductId());
        if(component==null)
            throw new IllegalArgumentException("Product not found");
        if(request.getMaximum()!=null)
            componentCatalogue.updateMax(component,request.getMaximum());
        if(request.getMinimum()!=null)
            componentCatalogue.updateMin(component,request.getMinimum());
        return new BaseResponseDTO(null,"0",null);
    }
}
