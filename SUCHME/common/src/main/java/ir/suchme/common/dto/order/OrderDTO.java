package ir.suchme.common.dto.order;

import java.util.Date;

/**
 * Created by mohammad on 6/9/17.
 */
public class OrderDTO {
    private String objectName;

    private Integer quantity;

    private Date created;

    private String id;


    public OrderDTO() {
    }

    @Override
    public String toString() {
        return "{" +
                "objectName='" + objectName + '\'' +
                ", quantity=" + quantity +
                ", created=" + created +
                ", id='" + id + '\'' +
                '}';
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderDTO(String objectName, Integer quantity, Date created, String id) {

        this.objectName = objectName;
        this.quantity = quantity;
        this.created = created;
        this.id = id;
    }
}
