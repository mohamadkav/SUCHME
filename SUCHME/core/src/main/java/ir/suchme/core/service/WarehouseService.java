package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.RequestUpdateComponentMinMaxDTO;
import ir.suchme.common.dto.component.SupplyComponentDTO;
import ir.suchme.common.dto.product.RequestChangeSupplyPathDTO;
import ir.suchme.common.dto.product.RequestGetDifferentPathsDTO;
import ir.suchme.common.dto.product.ResponseGetCurrentPathDTO;
import ir.suchme.common.dto.product.ResponseGetDifferentPathsDTO;
import ir.suchme.common.dto.supplier.SupplierDTO;
import ir.suchme.core.catalogue.ComponentCatalogue;
import ir.suchme.core.catalogue.ProductCatalogue;
import ir.suchme.core.catalogue.SupplyComponentCatalogue;
import ir.suchme.core.domain.entity.Component;
import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.entity.SupplyComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        HashMap<Component,List<SupplyComponent>> componentListHashMap=supplyComponentCatalogue.getDifferentPathsForProduct(product);

        HashMap<ComponentDTO,List<SupplyComponentDTO>> hashMap=new HashMap<>();
        for (Component component : componentListHashMap.keySet()) {
            List<SupplyComponentDTO> supplyComponentDTOS=new ArrayList<>();
            for(SupplyComponent supplyComponent:componentListHashMap.get(component))
                supplyComponentDTOS.add(new SupplyComponentDTO(null,new SupplierDTO(supplyComponent.getSupplier().getId().toString(),
                        supplyComponent.getSupplier().getName()),supplyComponent.getPrice(),supplyComponent.getTimeToSupply(),supplyComponent.getQuantity(),
                        supplyComponent.getId().toString()));
            hashMap.put(new ComponentDTO(component.getName(),null,component.getMaxValue(),component.getMinValue(),component.getId().toString(),
                    null,null,null),supplyComponentDTOS);
        }
        return new ResponseGetDifferentPathsDTO(null,"0",null,hashMap);
    }
    public ResponseGetCurrentPathDTO getCurrentPath(RequestGetDifferentPathsDTO request){
        Product product=productCatalogue.findById(request.getProductId());
        if(product==null)
            throw new IllegalArgumentException("Product not found");
        HashMap<Component,SupplyComponent> componentListHashMap=supplyComponentCatalogue.getCurrentSupplyPath(product);

        HashMap<ComponentDTO,SupplyComponentDTO> hashMap=new HashMap<>();
        for (Component component : componentListHashMap.keySet()) {
            SupplyComponent supplyComponent=componentListHashMap.get(component);
            hashMap.put(new ComponentDTO(component.getName(),null,component.getMaxValue(),component.getMinValue(),component.getId().toString(),
                    null,null,null),(new SupplyComponentDTO(null,new SupplierDTO(supplyComponent.getSupplier().getId().toString(),
                    supplyComponent.getSupplier().getName()),supplyComponent.getPrice(),supplyComponent.getTimeToSupply(),supplyComponent.getQuantity(),
                    supplyComponent.getId().toString())));
        }
        return new ResponseGetCurrentPathDTO(null,"0",null,hashMap);
    }
    public BaseResponseDTO changeCurrentPath(RequestChangeSupplyPathDTO request){
        Product product=productCatalogue.findById(request.getProduct().getId());
        if(product==null)
            throw new IllegalArgumentException("Product not found");
        HashMap<Component,SupplyComponent> componentListHashMap=new HashMap<>();

        for (ComponentDTO componentDTO : request.getNewPath().keySet()) {
            Component component=componentCatalogue.findOne(componentDTO.getId());
            SupplyComponent supplyComponent=supplyComponentCatalogue.findOne(request.getNewPath().get(componentDTO).getId());
            componentListHashMap.put(component,supplyComponent);
        }

        supplyComponentCatalogue.changeSupplyPath(product,componentListHashMap);
        return new BaseResponseDTO(null,"0",null);
    }
}
