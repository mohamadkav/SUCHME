package ir.suchme.common.dto.component;

/**
 * Created by mohammad on 6/9/17.
 */
public class ComponentDTO {
    private String name;

    private Integer price;

    private Integer maxValue;

    private Integer minValue;

    private String id;

    public ComponentDTO() {
    }

    public ComponentDTO(String name, Integer price, Integer maxValue, Integer minValue, String id) {
        this.name = name;
        this.price = price;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", price=" + price +
                ", maxValue=" + maxValue +
                ", minValue=" + minValue +
                ", id='" + id ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
