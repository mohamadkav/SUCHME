package ir.suchme.common.dto.order;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

import java.util.List;

/**
 * Created by mohammad on 6/25/17.
 */
public class RequestOrderProductDTO implements RequestDTO {

    private List<String> requirements;

    private String productName;

    private Integer quantity;

    public RequestOrderProductDTO() {
    }

    public RequestOrderProductDTO(List<String> requirements, String productName, Integer quantity) {
        this.requirements = requirements;
        this.productName = productName;
        this.quantity = quantity;
    }

    @Override
    public void validation() {
        Assertions.assertThat(requirements).isNotNull();
    }

    @Override
    public String toString() {
        return "RequestOrderProductDTO{" +
                "requirements=" + requirements +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
