package ir.suchme.common.dto.user;

import ir.suchme.common.dto.base.BaseResponseDTO;

import java.util.List;

/**
 * Created by mohammad on 6/21/17.
 */
public class ResponseUserActivityListDTO extends BaseResponseDTO {
    private List<UserActivityDTO> userActivityDTOS;

    public List<UserActivityDTO> getUserActivityDTOS() {
        return userActivityDTOS;
    }

    public void setUserActivityDTOS(List<UserActivityDTO> userActivityDTOS) {
        this.userActivityDTOS = userActivityDTOS;
    }
}
