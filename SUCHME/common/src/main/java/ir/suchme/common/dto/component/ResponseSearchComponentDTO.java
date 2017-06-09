package ir.suchme.common.dto.component;

import ir.suchme.common.dto.base.BaseResponseDTO;

import java.util.List;

/**
 * Created by mohammad on 6/9/17.
 */
public class ResponseSearchComponentDTO extends BaseResponseDTO{
    private List<ComponentDTO> componentDTOS;

    public List<ComponentDTO> getComponentDTOS() {
        return componentDTOS;
    }

    public void setComponentDTOS(List<ComponentDTO> componentDTOS) {
        this.componentDTOS = componentDTOS;
    }

    public ResponseSearchComponentDTO(String error, String responseCode, String userId, List<ComponentDTO> componentDTOS) {
        super(error, responseCode, userId);
        this.componentDTOS = componentDTOS;
    }

    public ResponseSearchComponentDTO(List<ComponentDTO> componentDTOS) {
        this.componentDTOS = componentDTOS;
    }

    public ResponseSearchComponentDTO() {
    }
}
