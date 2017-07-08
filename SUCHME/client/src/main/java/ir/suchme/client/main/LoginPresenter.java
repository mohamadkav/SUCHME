package ir.suchme.client.main;

import ir.suchme.client.util.NotificationUtil;
import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.user.RequestAuthenticateDTO;
import ir.suchme.common.dto.user.RequestCreateUserDTO;
import ir.suchme.common.dto.user.RequestUserForgotPasswordDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Farzin on 6/6/2017.
 */
public class LoginPresenter implements Initializable{

    @FXML private TextField loginUsername;
    @FXML private TextField loginPassword;
    @FXML private TextField createUsername;
    @FXML private TextField createPassword;
    @FXML private TextField createName;
    @FXML private TextField createEmail;
    @FXML private Button loginButton;
    @FXML private Button signUpButton;
    @FXML private Button changePasswordButton;
    @FXML private Label changePasswordText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/user/login", new RequestAuthenticateDTO(loginUsername.getText(),loginPassword.getText()), BaseResponseDTO.class);
            NotificationUtil.OK(out);
        });
        signUpButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/user/create", new RequestCreateUserDTO(createUsername.getText(),createPassword.getText(),createName.getText(),createEmail.getText()), BaseResponseDTO.class);
            NotificationUtil.OK(out);
        });
        changePasswordButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/user/forgotPassword", new RequestUserForgotPasswordDTO(loginUsername.getText()), BaseResponseDTO.class);
            if(!NotificationUtil.check(out))
                changePasswordText.setText("ایمیلی حاوی رمز عبور جدید فرستاده شد");
        });

    }
}
