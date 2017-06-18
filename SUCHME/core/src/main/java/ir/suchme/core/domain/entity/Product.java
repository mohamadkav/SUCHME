package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;
import ir.suchme.core.domain.entity.enums.ProductType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mohammad on 6/18/17.
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    @Column
    private Integer price;

    @Column
    private String description;

    @Column
    private String name;

    @Column
    private Integer quantity;

    @OneToMany
    private Set<Comment> comments;

    public Product(ProductType productType, Integer price, String description, String name,Integer quantity, Set<Comment> comments) {
        this.productType = productType;
        this.price = price;
        this.description = description;
        this.name = name;
        this.comments = comments;
        this.quantity=quantity;
    }

    public Product() {
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
