package ir.suchme.common.dto.user;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

/**
 * Created by mohammad on 6/9/17.
 */
public class RequestCreateUserDTO implements RequestDTO{
    private String userName;
    private String password;
    private String name;
    private String email;

    @Override
    public void validation() {
        Assertions.assertThat(userName).isNotNull();
        Assertions.assertThat(password).isNotNull();
        Assertions.assertThat(name).isNotNull();
        Assertions.assertThat(email).isNotNull();
    }

    public RequestCreateUserDTO() {
    }

    public RequestCreateUserDTO(String userName, String password, String name, String email) {

        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
