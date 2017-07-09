package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.RequestUpdateComponentMinMaxDTO;
import ir.suchme.common.dto.product.RequestGetDifferentPathsDTO;
import ir.suchme.common.dto.product.ResponseGetDifferentPathsDTO;
import ir.suchme.core.catalogue.ComponentCatalogue;
import ir.suchme.core.catalogue.ProductCatalogue;
import ir.suchme.core.catalogue.SupplyComponentCatalogue;
import ir.suchme.core.domain.entity.Component;
import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mohammad on 6/9/17.
 */

@Service
public class WarehouseService {

    private final ComponentCatalogue componentCatalogue;
    private final ProductCatalogue productCatalogue;
    private final SupplyComponentCatalogue supplyComponentCatalogue;

    @Autowired
    public WarehouseService(ComponentCatalogue componentCatalogue, ProductCatalogue productCatalogue, SupplyComponentCatalogue supplyComponentCatalogue) {
        this.componentCatalogue = componentCatalogue;
        this.productCatalogue = productCatalogue;
        this.supplyComponentCatalogue = supplyComponentCatalogue;
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

    public ResponseGetDifferentPathsDTO getDifferentPaths(RequestGetDifferentPathsDTO request){
        Product product=productCatalogue.findById(request.getProductId());
        if(product==null)
            throw new IllegalArgumentException("Product not found");
        HashMap<Component,List<Supplier>> componentListHashMap=supplyComponentCatalogue.getDifferentPathsForProduct(product);
    }
}
