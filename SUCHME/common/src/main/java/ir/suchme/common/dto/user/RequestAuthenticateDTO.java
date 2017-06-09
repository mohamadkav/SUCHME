package ir.suchme.common.dto.user;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

/**
 * Created by mohammad on 5/21/17.
 */
public class RequestAuthenticateDTO implements RequestDTO {
    private String userName;
    private String password;

    @Override
    public void validation() {
        Assertions.assertThat(userName).isNotNull();
        Assertions.assertThat(password).isNotNull();
    }

    public RequestAuthenticateDTO() {
    }

    public RequestAuthenticateDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
