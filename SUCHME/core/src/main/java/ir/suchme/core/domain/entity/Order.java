package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by mohammad on 6/25/17.
 */
@Entity
@Table(name = "ORDERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ORDER_TYPE")
public class Order extends BaseEntity{

    @Column(name = "QUANTITY",nullable = false)
    private Integer quantity;

    @Column(name = "PRICE")
    private Integer price;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
