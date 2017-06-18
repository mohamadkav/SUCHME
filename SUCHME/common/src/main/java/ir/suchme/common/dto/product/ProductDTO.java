package ir.suchme.common.dto.product;

/**
 * Created by mohammad on 6/18/17.
 */
public class ProductDTO {
    private String id;

    private Integer price;

    private String name;

    private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(String id, Integer price, String name,Integer quantity) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.quantity=quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
