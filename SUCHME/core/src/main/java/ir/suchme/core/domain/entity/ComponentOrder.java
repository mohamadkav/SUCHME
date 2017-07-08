package ir.suchme.core.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by mohammad on 6/25/17.
 */
@Entity
@DiscriminatorValue(value = "COMPONENT")
public class ComponentOrder extends Order {
    @ManyToOne
    @JoinColumn(name = "COMPONENTID")
    private Component  component;

    @ManyToOne
    @JoinColumn(name = "SUPPLIERID")
    private Supplier supplier;


    public ComponentOrder(Component component,Supplier supplier, Date created,Integer quantity) {
        this.component = component;
        setCreated(created);
        setQuantity(quantity);
        this.supplier=supplier;
    }

    public ComponentOrder() {
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
