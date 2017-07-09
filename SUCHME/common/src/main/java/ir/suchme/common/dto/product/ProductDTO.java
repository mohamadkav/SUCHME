package ir.suchme.common.dto.product;

import java.util.List;

/**
 * Created by mohammad on 6/18/17.
 */
public class ProductDTO {
    private String id;

    private Integer price;

    private String name;

    private List<RequirmentDTO> requirmentDTOS;


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

    public List<RequirmentDTO> getRequirmentDTOS() {
        return requirmentDTOS;
    }

    public void setRequirmentDTOS(List<RequirmentDTO> requirmentDTOS) {
        this.requirmentDTOS = requirmentDTOS;
    }
}
