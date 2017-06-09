package ir.suchme.client;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.user.RequestAuthenticateDTO;
import ir.suchme.common.dto.user.RequestCreateUserDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/user/login", new RequestAuthenticateDTO(loginUsername.getText(),loginPassword.getText()), BaseResponseDTO.class);
            System.out.println(out.getUserId());
        });
        signUpButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/user/create", new RequestCreateUserDTO(createUsername.getText(),createPassword.getText(),createName.getText(),createEmail.getText()), BaseResponseDTO.class);
            if(!Objects.equals(out.getResponseCode(), "0")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText(out.getError());
                alert.showAndWait();
            }
        });

    }
}
