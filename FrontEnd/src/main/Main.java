package main;

import dbConnector.MainDataConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

//import backend.MainDataConnection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            MainDataConnection.init();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashbordFront.fxml"));
            primaryStage.setTitle("Clinic Managment app");
            primaryStage.setScene(new Scene(root, 1080, 720));
            primaryStage.show();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.toString());
            a.showAndWait();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
