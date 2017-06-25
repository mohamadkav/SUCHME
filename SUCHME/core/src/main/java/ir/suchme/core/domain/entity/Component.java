package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mohammad on 6/9/17.
 */
@Entity
@Table(name = "COMPONENT")
public class Component extends BaseEntity {
    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer minValue;

    @Column
    private Integer maxValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public Component() {
    }

    public Component(String name, Integer price, Integer minValue, Integer maxValue, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.supplier = supplier;
    }

    //    @ManyToMany(mappedBy = "components", fetch = FetchType.LAZY)
//    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
