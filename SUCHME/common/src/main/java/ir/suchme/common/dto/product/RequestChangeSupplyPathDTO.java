package ir.suchme.common.dto.product;

import ir.suchme.common.dto.base.RequestDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.SupplyComponentDTO;

import java.util.HashMap;

/**
 * Created by mohammad on 6/18/17.
 */
public class RequestChangeSupplyPathDTO implements RequestDTO{
    private ProductDTO product;
    private HashMap<ComponentDTO,SupplyComponentDTO> newPath;
    @Override
    public void validation() {

    }

    @Override
    public String toString() {
        return "{" +
                "product=" + product +
                ", newPath=" + newPath +
                '}';
    }

    public RequestChangeSupplyPathDTO(ProductDTO product, HashMap<ComponentDTO, SupplyComponentDTO> newPath) {
        this.product = product;
        this.newPath = newPath;
    }

    public RequestChangeSupplyPathDTO() {
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public HashMap<ComponentDTO, SupplyComponentDTO> getNewPath() {
        return newPath;
    }

    public void setNewPath(HashMap<ComponentDTO, SupplyComponentDTO> newPath) {
        this.newPath = newPath;
    }
}
