package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;
import ir.suchme.core.domain.entity.enums.ProductState;
import ir.suchme.core.domain.entity.enums.ProductType;

import javax.persistence.*;
import java.lang.*;
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

    @Enumerated(value = EnumType.STRING)
    private ProductState productState;

    @Column
    private Integer price;

    @Column
    private String description;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Requirement> requirements;


    @OneToMany
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_COMPONENT", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "component_supply_id",
                    nullable = false, updatable = false) })
    private Set<SupplyComponent> supplyComponents;


    @ManyToOne
    @JoinColumn(name = "PARENT_PRODUCT")
    private Product parentProduct;

    @OneToMany(mappedBy="parentProduct", cascade = CascadeType.ALL)
    private Set<Product> subProducts;


    public Product(ProductType productType, ProductState productState, Integer price, String description, String name, Set<Requirement> requirements, Set<Comment> comments, Set<SupplyComponent> supplyComponents, Product parentProduct, Set<Product> subProducts) {
        this.productType = productType;
        this.productState = productState;
        this.price = price;
        this.description = description;
        this.name = name;
        this.requirements = requirements;
        this.comments = comments;
        this.supplyComponents = supplyComponents;
        this.parentProduct = parentProduct;
        this.subProducts = subProducts;
    }

    public Product() {
    }

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        this.requirements = requirements;
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

    public Set<SupplyComponent> getSupplyComponents() {
        return supplyComponents;
    }

    public void setSupplyComponents(Set<SupplyComponent> supplyComponents) {
        this.supplyComponents = supplyComponents;
    }

    public ProductState getProductState() {
        return productState;
    }

    public void setProductState(ProductState productState) {
        this.productState = productState;
    }

    public HashSet<Supplier> getAllSuppliers()
    {
        HashSet<Supplier> suppliers = new HashSet<>();
        for (SupplyComponent supplyComponent : getSupplyComponents())
        {
            suppliers.add(supplyComponent.getSupplier());
        }
        return suppliers;
    }

    public boolean isSimilarTo(Product product)
    {
        if (this.getAllSuppliers().equals(product.getAllSuppliers()))
            return true;
        else return false;
    }

    public Process getManufactureProcess()
    {
        Process process = new Process(this);
        process.setSupplyComponents(this.getSupplyComponents());
        return process;
    }
}
