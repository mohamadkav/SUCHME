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

    private String supplierName;

    private String supplierId;

    private Integer timeToSupply;

    public ComponentDTO() {
    }

    public ComponentDTO(String name, Integer price, Integer maxValue, Integer minValue, String id, String supplierName, String supplierId, Integer timeToSupply) {
        this.name = name;
        this.price = price;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.id = id;
        this.supplierName = supplierName;
        this.supplierId = supplierId;
        this.timeToSupply = timeToSupply;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getTimeToSupply() {
        return timeToSupply;
    }

    public void setTimeToSupply(Integer timeToSupply) {
        this.timeToSupply = timeToSupply;
    }
}
