package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

public class DashbordClerk {


    @FXML
    private AnchorPane parent;




    @FXML
    public void patientEntryClick(ActionEvent e) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordPatient.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }

    @FXML
    public void billingClick(ActionEvent e){

        ///alert box and table

        TextInputDialog dialog=new TextInputDialog("");
        dialog.setTitle("Bill");
        dialog.setHeaderText("Medicine Details");
        dialog.setContentText("Patient id: ");

        Optional<String > result=dialog.showAndWait();
        if(result.isPresent()){
            System.out.println("Patient id" +result.get());
        }



    }
    @FXML
    public void medicineClick(ActionEvent e) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordMedicine.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }


    public void backClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordFront.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }

    public void clerkPageClick(ActionEvent actionEvent) {
    }

    public void doctorPageClick(ActionEvent actionEvent) {
    }
}
