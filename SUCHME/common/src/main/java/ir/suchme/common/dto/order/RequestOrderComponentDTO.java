package ir.suchme.common.dto.order;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

import java.util.UUID;

/**
 * Created by mohammad on 6/25/17.
 */
public class RequestOrderComponentDTO implements RequestDTO {
    private String componentId;

    private Integer quantity;

    public RequestOrderComponentDTO() {
    }

    public RequestOrderComponentDTO(String componentId, Integer quantity) {
        this.componentId = componentId;
        this.quantity = quantity;
    }

    @Override
    public void validation() {
        Assertions.assertThat(componentId).isNotNull();
        Assertions.assertThat(quantity).isNotNull();
        try{
            UUID.fromString(componentId);
        }catch (Exception e){
            throw new AssertionError("componentId is not a UUID",e);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "componentId='" + componentId + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
