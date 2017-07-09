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
                ", id='" + id + '\'' +
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
}
