package ir.suchme.client;

/**
 * Created by mohammad on 5/26/17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MathFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));

        Scene scene = new Scene(root, 1002, 756);

        primaryStage.setTitle("SUpply CHange Management system (SUCHME)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
