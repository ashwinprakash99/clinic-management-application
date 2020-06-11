package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DashbordFront {

    @FXML
    private Button clerkBtn;

    @FXML
    private Button doctorBtn;

    @FXML
    private Button detailsBtn;

    @FXML
    private AnchorPane dashbordfront;


    @FXML
    public void clerkClick(ActionEvent e ) throws Exception{

        dashbordfront.getScene().getWindow().hide();

        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }

    @FXML
    public void doctorClick(ActionEvent e ) throws Exception{

        dashbordfront.getScene().getWindow().hide();

        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }

    @FXML
    public void detailClick(ActionEvent e ) throws Exception{

        dashbordfront.getScene().getWindow().hide();

        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordDetail.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }

}
