package ir.suchme.common.dto.supplier;

/**
 * Created by mohammad on 6/18/17.
 */
public class SupplierDTO {
    private String id;

    private String name;

    public SupplierDTO() {
    }


    public SupplierDTO(String id, String name) {

        this.id = id;
        this.name = name;
    }

    public SupplierDTO(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
