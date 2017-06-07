package ir.suchme.client;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainController implements Initializable {

    @FXML private Pane primaryPane;
    @FXML private Label label;
    @FXML private Button exit;
    @FXML private MenuBar menuBar;
    @FXML private MenuItem customersActivityReport;
    @FXML private MenuItem employeesActivityReport;
    @FXML private MenuItem processReport;
    @FXML private MenuItem productBalanceReport;
    @FXML private MenuItem employeeActivityReport;




    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customersActivityReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getClassLoader().getResource("customers-activity.fxml"));
                    primaryPane.getChildren().clear();
                    primaryPane.getChildren().add(parent);
//                    stage.show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

        employeeActivityReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getClassLoader().getResource("employee-activity.fxml"));
                    primaryPane.getChildren().clear();
                    primaryPane.getChildren().add(parent);
//                    stage.show();
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