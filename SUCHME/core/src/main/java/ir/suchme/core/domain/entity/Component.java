package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;
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
    private Integer minValue;

    @Column
    private Integer maxValue;


    @OneToMany(mappedBy="component", targetEntity=SupplyComponent.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SupplyComponent> supplyComponents;

    public Component() {
    }

    public Component(String name, Integer minValue, Integer maxValue, Set<SupplyComponent> supplyComponents) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.supplyComponents = supplyComponents;
    }

    //    @ManyToMany(mappedBy = "components", fetch = FetchType.LAZY)
//    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<SupplyComponent> getSupplyComponents() {
        return supplyComponents;
    }

    public void setSupplyComponents(Set<SupplyComponent> supplyComponents) {
        this.supplyComponents = supplyComponents;
    }
}
