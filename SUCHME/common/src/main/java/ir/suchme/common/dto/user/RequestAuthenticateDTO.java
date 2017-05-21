package ir.suchme.common.dto.user;

import com.sun.tools.javac.util.Assert;
import ir.suchme.common.dto.base.RequestDTO;

/**
 * Created by mohammad on 5/21/17.
 */
public class RequestAuthenticateDTO implements RequestDTO {
    private String userName;
    private String password;

    @Override
    public void validation() {
        Assert.checkNonNull(userName);
        Assert.checkNonNull(password);
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
