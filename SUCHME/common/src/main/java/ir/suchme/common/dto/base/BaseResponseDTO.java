package ir.suchme.common.dto.base;

/**
 * Created by mohammad on 5/21/17.
 */
public class BaseResponseDTO {
    private String error;
    private String responseCode;
    private String userId;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
