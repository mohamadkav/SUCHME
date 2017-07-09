package ir.suchme.common.dto.product;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.SupplyComponentDTO;
import ir.suchme.common.util.ComponentDeserializer;

import java.util.HashMap;

/**
 * Created by mohammad on 6/18/17.
 */
public class ResponseGetCurrentPathDTO extends BaseResponseDTO{
    @JsonDeserialize(keyUsing = ComponentDeserializer.class)
    private HashMap<ComponentDTO,SupplyComponentDTO> pathDTOs;

    @Override
    public String toString() {
        return "{" +
                "pathDTOs=" + pathDTOs +
                '}';
    }

    public ResponseGetCurrentPathDTO() {
    }

    public ResponseGetCurrentPathDTO(String error, String responseCode, String userId, HashMap<ComponentDTO, SupplyComponentDTO> pathDTOs) {

        super(error, responseCode, userId);
        this.pathDTOs = pathDTOs;
    }

    public ResponseGetCurrentPathDTO(HashMap<ComponentDTO, SupplyComponentDTO> pathDTOs) {
        this.pathDTOs = pathDTOs;
    }

    public HashMap<ComponentDTO, SupplyComponentDTO> getPathDTOs() {
        return pathDTOs;
    }

    public void setPathDTOs(HashMap<ComponentDTO, SupplyComponentDTO> pathDTOs) {
        this.pathDTOs = pathDTOs;
    }
}
