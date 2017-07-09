package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Farzin on 6/21/2017.
 */
@Entity
@Table(name = "SUPPLIER")
public class Supplier extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="supplier", targetEntity=SupplyComponent.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SupplyComponent> supplyComponents;



    public Supplier(String name) {
        this.name = name;
    }

    public Supplier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SupplyComponent> getSupplyComponents() {
        return supplyComponents;
    }

    public void setSupplyComponents(Set<SupplyComponent> supplyComponents) {
        this.supplyComponents = supplyComponents;
    }

    public void addToSupplyComponents(SupplyComponent supplyComponent) {
        this.supplyComponents.add(supplyComponent);
    }
}
