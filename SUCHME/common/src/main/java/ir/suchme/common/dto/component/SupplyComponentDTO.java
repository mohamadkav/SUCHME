package ir.suchme.common.dto.component;

import ir.suchme.common.dto.supplier.SupplierDTO;

import java.awt.*;

/**
 * Created by Farzin on 7/9/2017.
 */
public class SupplyComponentDTO {

    private ComponentDTO component;

    private SupplierDTO supplier;

    private Integer price;

    private Integer timeToSupply;

    private Integer quantity;

    private String id;

    @Override
    public String toString() {
        return "{" +
                "component=" + component +
                ", supplier=" + supplier +
                ", price=" + price +
                ", timeToSupply=" + timeToSupply +
                ", quantity=" + quantity +
                '}';
    }

    public SupplyComponentDTO() {
    }

    public SupplyComponentDTO(ComponentDTO component, SupplierDTO supplier, Integer price, Integer timeToSupply, Integer quantity, String id) {

        this.component = component;
        this.supplier = supplier;
        this.price = price;
        this.timeToSupply = timeToSupply;
        this.quantity = quantity;
        this.id = id;
    }

    public ComponentDTO getComponent() {
        return component;
    }

    public void setComponent(ComponentDTO component) {
        this.component = component;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTimeToSupply() {
        return timeToSupply;
    }

    public void setTimeToSupply(Integer timeToSupply) {
        this.timeToSupply = timeToSupply;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
