package ir.suchme.client;

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
    @FXML private MenuItem customersActivityReport;
    @FXML private MenuItem employeesActivityReport;
    @FXML private MenuItem processReport;
    @FXML private MenuItem productBalanceReport;
    @FXML private MenuItem employeeActivityReport;
    @FXML private MenuItem assignMinMaxMenu;




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