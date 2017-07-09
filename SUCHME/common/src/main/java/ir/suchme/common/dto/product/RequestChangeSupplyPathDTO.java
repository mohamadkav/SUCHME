package ir.suchme.common.dto.product;

import ir.suchme.common.dto.base.RequestDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.SupplyComponentDTO;

import java.util.List;

/**
 * Created by mohammad on 6/18/17.
 */
public class RequestChangeSupplyPathDTO implements RequestDTO{
    private ProductDTO product;
    private List<ComponentDTO>componentDTOS;
    private List<SupplyComponentDTO> supplyComponentDTOS;
    @Override
    public void validation() {

    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public List<ComponentDTO> getComponentDTOS() {
        return componentDTOS;
    }

    public void setComponentDTOS(List<ComponentDTO> componentDTOS) {
        this.componentDTOS = componentDTOS;
    }

    public List<SupplyComponentDTO> getSupplyComponentDTOS() {
        return supplyComponentDTOS;
    }

    public void setSupplyComponentDTOS(List<SupplyComponentDTO> supplyComponentDTOS) {
        this.supplyComponentDTOS = supplyComponentDTOS;
    }

    public RequestChangeSupplyPathDTO() {

    }

    public RequestChangeSupplyPathDTO(ProductDTO product, List<ComponentDTO> componentDTOS, List<SupplyComponentDTO> supplyComponentDTOS) {

        this.product = product;
        this.componentDTOS = componentDTOS;
        this.supplyComponentDTOS = supplyComponentDTOS;
    }
}
