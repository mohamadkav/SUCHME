package ir.suchme.common.dto.process;

import ir.suchme.common.dto.base.BaseResponseDTO;

import java.util.List;

/**
 * Created by Farzin on 7/9/2017.
 */
public class ResponseProcessReportDTO extends BaseResponseDTO {

    private List<ProcessDTO> processDTOS;

    public List<ProcessDTO> getProcessDTOS() {
        return processDTOS;
    }

    public void setProcessDTOS(List<ProcessDTO> processDTOS) {
        this.processDTOS = processDTOS;
    }
}
