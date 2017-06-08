package ir.suchme.client;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.user.RequestAuthenticateDTO;
import ir.suchme.common.dto.user.ResponseUserDTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Farzin on 6/6/2017.
 */
public class UserAccountPresenter implements Initializable{

    @FXML private Label name;
    @FXML private Label lastName;
    @FXML private Label email;
    @FXML private Label phone;
    @FXML private Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequestAuthenticateDTO dto = new RequestAuthenticateDTO();
        dto.setUserName("farzin");
        dto.setPassword("123456");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SuchmeClient client = SuchmeClient.getInstance();
                BaseResponseDTO out = client.postRequestAndWaitForResponse("/user/login", dto, ResponseUserDTO.class);
                System.out.println(out.getUserId());
            }
        });

        String n = "farzin";
        String ln = "hooshmand";
        String m = "farzin_houshmand@yahoo.com";
        String p = "09122204589";


        name.setText(n);
        lastName.setText(ln);
        email.setText(m);
        phone.setText(p);


    }
}
