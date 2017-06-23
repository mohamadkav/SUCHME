package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;
import ir.suchme.core.domain.entity.enums.ProductType;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_COMPONENT", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "component_id",
                    nullable = false, updatable = false) })
    private Set<Component> components;


    @ManyToOne
    @JoinColumn(name = "PARENT_PRODUCT")
    public Product parentProduct;

    @OneToMany(mappedBy="parentProduct", cascade = CascadeType.ALL)
    public Set<Product> subProducts;


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

    public Set<Component> getComponents() {
        return components;
    }

    public void setComponents(Set<Component> components) {
        this.components = components;
    }

    public Product getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(Product parentProduct) {
        this.parentProduct = parentProduct;
    }

    public Set<Product> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(Set<Product> subProducts) {
        this.subProducts = subProducts;
    }

    public HashSet<Supplier> getAllSuppliers()
    {
        HashSet<Supplier> suppliers = new HashSet<>();
        for (Component component : getComponents())
        {
            suppliers.add(component.getSupplier());
        }
        return suppliers;
    }

    public boolean isSimilarTo(Product product)
    {
        if(this.getComponents().equals(product.getComponents()))
        {
            if (this.getAllSuppliers().equals(product.getAllSuppliers()))
                return true;
            else return false;
        }
        else
            return false;
    }
}
