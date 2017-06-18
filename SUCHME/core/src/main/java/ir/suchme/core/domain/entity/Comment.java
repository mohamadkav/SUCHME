package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by mohammad on 6/18/17.
 */
@Entity
@Table(name = "COMMENT")
public class Comment extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "USERID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRODUCTID")
    private Product product;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
