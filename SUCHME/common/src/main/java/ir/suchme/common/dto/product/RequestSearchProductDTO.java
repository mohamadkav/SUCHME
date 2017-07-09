package ir.suchme.common.dto.product;

import ir.suchme.common.dto.base.RequestDTO;

/**
 * Created by mohammad on 6/18/17.
 */
public class RequestSearchProductDTO implements RequestDTO{
    private String name;

    private String state;

    @Override
    public void validation() {

    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }

    public RequestSearchProductDTO(String name) {
        this.name = name;
    }

    public RequestSearchProductDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
