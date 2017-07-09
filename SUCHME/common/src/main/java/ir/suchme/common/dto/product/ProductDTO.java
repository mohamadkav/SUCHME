package ir.suchme.common.dto.product;

/**
 * Created by mohammad on 6/18/17.
 */
public class ProductDTO {
    private String id;

    private Integer price;

    private String name;

    public ProductDTO() {
    }

    public ProductDTO(String id, Integer price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
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
        return "{" +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
