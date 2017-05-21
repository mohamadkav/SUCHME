package ir.suchme.common.dto.user;


import ir.suchme.common.dto.base.BaseResponseDTO;

/**
 * Created by mohammad on 5/21/17.
 */
public class ResponseUserDTO extends BaseResponseDTO {

    public ResponseUserDTO(String id, String responseCode) {
        setUserId(id);
        setResponseCode(responseCode);
    }

    public ResponseUserDTO() {
    }
}
