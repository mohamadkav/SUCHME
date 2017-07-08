package ir.suchme.core.domain;

import ir.suchme.core.domain.entity.Order;
import ir.suchme.core.domain.entity.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by mohammad on 7/8/17.
 */

@Entity
@DiscriminatorValue(value = "PRODUCT")
public class ProductOrder extends Order{
    @ManyToOne
    @JoinColumn(name = "PRODUCTID")
    private Product product;

    public ProductOrder(Product product, Date created, Integer quantity) {
        this.product = product;
        setCreated(created);
        setQuantity(quantity);
    }

    public ProductOrder(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
