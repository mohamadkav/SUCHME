package ir.suchme.common.dto.process;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

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

    private Set<String> supplyComponentsId;

    private Set<String> productsId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Set<String> getSupplyComponentsId() {
        return supplyComponentsId;
    }

    public void setSupplyComponentsId(Set<String> supplyComponentsId) {
        this.supplyComponentsId = supplyComponentsId;
    }

    public Set<String> getProductsId() {
        return productsId;
    }

    public void setProductsId(Set<String> productsId) {
        this.productsId = productsId;
    }
}
