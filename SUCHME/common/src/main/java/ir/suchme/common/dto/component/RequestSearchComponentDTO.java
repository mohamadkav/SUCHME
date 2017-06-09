package ir.suchme.common.dto.component;

import ir.suchme.common.dto.base.RequestDTO;

/**
 * Created by mohammad on 6/9/17.
 */
public class RequestSearchComponentDTO implements RequestDTO {

    private String name;

    @Override
    public void validation() {
    }

    public RequestSearchComponentDTO(String name) {
        this.name = name;
    }

    public RequestSearchComponentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
