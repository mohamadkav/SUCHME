package ir.suchme.common.dto.order;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

import java.util.UUID;

/**
 * Created by mohammad on 6/25/17.
 */
public class RequestOrderComponentDTO implements RequestDTO {
    private String componentId;

    private String supplierId;

    private String supplierName;

    private String componentName;

    private Integer price;

    private Integer quantity;

    public RequestOrderComponentDTO() {
    }

    public RequestOrderComponentDTO(String componentId, String supplierId, String supplierName, String componentName, Integer price, Integer quantity) {
        this.componentId = componentId;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.componentName = componentName;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public void validation() {
        Assertions.assertThat(quantity).isNotNull();
/*
        Assertions.assertThat(componentId).isNotNull();
        try{
            UUID.fromString(componentId);
        }catch (Exception e){
            throw new AssertionError("componentId is not a UUID",e);
        }*/
    }

    @Override
    public String toString() {
        return "{" +
                "componentId='" + componentId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", componentName='" + componentName + '\'' +
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
