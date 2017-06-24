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

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<Component> components;

    public Supplier(String name) {
        this.name = name;
        components = new HashSet<>();
    }

    public Supplier() {
    }

    public String getName() {
        return name;
    }

    public Set<Component> getComponents() {
        return components;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComponents(Set<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component)
    {
        this.components.add(component);
    }

    public void addComponents(Set<Component> components)
    {
        this.components.addAll(components);
    }
}
