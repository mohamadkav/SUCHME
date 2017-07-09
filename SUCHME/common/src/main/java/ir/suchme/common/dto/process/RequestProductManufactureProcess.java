package ir.suchme.common.dto.process;

import ir.suchme.common.dto.base.RequestDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Set;

/**
 * Created by Farzin on 7/9/2017.
 */
public class RequestProductManufactureProcess implements RequestDTO {

    @Override
    public void validation() {
        Assertions.assertThat(productId).isNotNull();
    }

    private String productId;

    private List<ComponentDTO> componentDTOS;

    private Set<String> productsId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<ComponentDTO> getComponentDTOS() {
        return componentDTOS;
    }

    public void setComponentDTOS(List<ComponentDTO> componentDTOS) {
        this.componentDTOS = componentDTOS;
    }

    public Set<String> getProductsId() {
        return productsId;
    }

    public void setProductsId(Set<String> productsId) {
        this.productsId = productsId;
    }
}
