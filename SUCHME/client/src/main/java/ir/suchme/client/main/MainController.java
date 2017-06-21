package ir.suchme.client.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML private Pane primaryPane;
    @FXML private Label label;
    @FXML private Button exit;
    @FXML private MenuBar menuBar;
    @FXML private MenuItem userActivityReport;
    @FXML private MenuItem employeeActivityReport;
    @FXML private MenuItem processReport;
    @FXML private MenuItem productBalanceReport;
    @FXML private MenuItem assignMinMaxMenu;
    @FXML private MenuItem getQuantityMenu;




    @Override
    public void initialize(URL url, ResourceBundle rb) {


        employeeActivityReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getClassLoader().getResource("employee-activity.fxml"));
                    primaryPane.getChildren().clear();
                    primaryPane.getChildren().add(parent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        userActivityReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getClassLoader().getResource("user-activity.fxml"));
                    primaryPane.getChildren().clear();
                    primaryPane.getChildren().add(parent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        assignMinMaxMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getClassLoader().getResource("assign-minmax.fxml"));
                    primaryPane.getChildren().clear();
                    primaryPane.getChildren().add(parent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        getQuantityMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getClassLoader().getResource("product-quantity.fxml"));
                    primaryPane.getChildren().clear();
                    primaryPane.getChildren().add(parent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Platform.exit();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}