package ir.suchme.client;

import com.google.gson.Gson;
import ir.suchme.common.dto.user.RequestAuthenticateDTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import ir.suchme.core.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

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
        dto.setUserName("farzin1");
        dto.setPassword("123");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SuchmeClient client = SuchmeClient.getInstance();
                String out = client.postRequestAndWaitForResponse("/user/login", dto);
                System.out.println(out);
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
