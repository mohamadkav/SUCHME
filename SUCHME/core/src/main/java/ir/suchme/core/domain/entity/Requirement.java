package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by mohammad on 6/18/17.
 */
@Entity
@Table(name = "REQUIREMENT")
public class Requirement extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "PRODUCTID")
    private Product product;

    @Column
    private String description;

    public Requirement(Product product, String description) {
        this.product = product;
        this.description = description;
    }

    public Requirement() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
