package ir.suchme.common.dto.user;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

/**
 * Created by mohammad on 6/21/17.
 */
public class RequestUserForgotPasswordDTO implements RequestDTO{
    private String userName;

    public RequestUserForgotPasswordDTO() {
    }

    public RequestUserForgotPasswordDTO(String userName) {
        this.userName = userName;
    }

    @Override
    public void validation() {
        Assertions.assertThat(userName).isNotNull();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
