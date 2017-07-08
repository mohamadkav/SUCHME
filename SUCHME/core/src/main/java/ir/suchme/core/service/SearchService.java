package ir.suchme.core.service;

import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.RequestSearchComponentDTO;
import ir.suchme.common.dto.component.ResponseSearchComponentDTO;
import ir.suchme.common.dto.product.ProductDTO;
import ir.suchme.common.dto.product.RequestSearchProductDTO;
import ir.suchme.common.dto.product.ResponseSearchProductDTO;
import ir.suchme.common.dto.supplier.RequestSearchSupplierDTO;
import ir.suchme.common.dto.supplier.ResponseSearchSupplierDTO;
import ir.suchme.common.dto.supplier.SupplierDTO;
import ir.suchme.core.catalogue.ComponentCatalogue;
import ir.suchme.core.catalogue.ProductCatalogue;
import ir.suchme.core.catalogue.SupplierCatalogue;
import ir.suchme.core.domain.entity.Component;
import ir.suchme.core.domain.entity.Product;
import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.entity.SupplyComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammad on 6/9/17.
 */

@Service
public class SearchService {

    private final ComponentCatalogue componentCatalogue;

    private final ProductCatalogue productCatalogue;

    private final SupplierCatalogue supplierCatalogue;

    @Autowired
    public SearchService(ComponentCatalogue componentCatalogue, ProductCatalogue productCatalogue, SupplierCatalogue supplierCatalogue) {
        this.componentCatalogue = componentCatalogue;
        this.productCatalogue=productCatalogue;
        this.supplierCatalogue = supplierCatalogue;
    }

    public ResponseSearchComponentDTO searchComponent(RequestSearchComponentDTO request){
        List<ComponentDTO> componentDTOs=new ArrayList<>();
        for(Component component:componentCatalogue.search(request.getName())) {
            for (SupplyComponent supplyComponent : component.getSupplyComponents())
                componentDTOs.add(new ComponentDTO(component.getName(), supplyComponent.getPrice(), component.getMaxValue(), component.getMinValue(), component.getId().toString(), supplyComponent.getSupplier().getName(),
                        supplyComponent.getSupplier().getId().toString(),
                        supplyComponent.getTimeToSupply()));
        }
        return new ResponseSearchComponentDTO(null,"0",null,componentDTOs);
    }
    public ResponseSearchProductDTO searchProduct(RequestSearchProductDTO request){
        List<ProductDTO> productDTOS=new ArrayList<>();
        for(Product product:productCatalogue.search(request.getName()))
            productDTOS.add(new ProductDTO(product.getId().toString(),product.getPrice(),product.getName(),product.getQuantity()));
        return new ResponseSearchProductDTO(null,"0",null,productDTOS);
    }

    public ResponseSearchSupplierDTO searchSupplier(RequestSearchSupplierDTO request){
        List<SupplierDTO> supplierDTOS=new ArrayList<>();
        for(Supplier supplier:supplierCatalogue.search(request.getName()))
            supplierDTOS.add(new SupplierDTO(supplier.getId().toString(),supplier.getName()));
        return new ResponseSearchSupplierDTO(null,"0",null,supplierDTOS);
    }
}
