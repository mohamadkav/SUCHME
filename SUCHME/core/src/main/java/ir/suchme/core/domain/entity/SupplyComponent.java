package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.Component;
import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by Farzin on 7/8/2017.
 */
@Entity
@Table(name = "SUPPLY_COMPONENT")
public class SupplyComponent extends BaseEntity{

    @Column
    private Integer price;

    @Column
    private Integer timeToSupply;

    @Column
    private Integer quantity;

    @ManyToOne(fetch= FetchType.LAZY)
    private Supplier supplier;

    @ManyToOne(fetch= FetchType.LAZY)
    private Component component;

    public SupplyComponent(int price, int timeToSupply, Supplier supplier, Component component) {
        this.price = price;
        this.timeToSupply = timeToSupply;
        this.supplier = supplier;
        this.component = component;
    }

    public SupplyComponent() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTimeToSupply() {
        return timeToSupply;
    }

    public void setTimeToSupply(int timeToSupply) {
        this.timeToSupply = timeToSupply;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
