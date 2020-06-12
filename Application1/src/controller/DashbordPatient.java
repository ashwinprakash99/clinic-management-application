package controller;

import dbConnector.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class DashbordPatient {

    @FXML
    private AnchorPane parent;
    @FXML
    private Button addPatientBtn;

    @FXML
    private Button updatePateintBtn;

    @FXML
    private Button deletePateintBtn;

    @FXML
    void addPatientClick(ActionEvent event) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/addPatient.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }

    @FXML
    void deletePatientClick(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Patient");
        GridPane gridPane=new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(20,20,20,20));
        TextField pid=new TextField();
        pid.setPromptText("Patient ID ");
        gridPane.add(pid,0,0);
        alert.getDialogPane().setContent(gridPane);
        Optional<ButtonType> result=alert.showAndWait();

        if(result.get()==ButtonType.OK){
            if(!pid.getText().equals("")) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setContentText("Are you sure of deleting the patient?");
                Optional<ButtonType> res = alert1.showAndWait();
                if (res.get() == ButtonType.OK) {


                    boolean v = Patient.removePatient(Long.parseLong(pid.getText()));
                    if (v == true) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Patient Deleted");
                        alert2.setContentText("Patient id " + pid.getText());
                        alert2.showAndWait();
                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Patient Not Found");
                        alert2.setContentText("Patient is not present");
                        alert2.showAndWait();

                    }
                }
            }
            else{
                Alert alert3=new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Error");
                alert3.setContentText("Field are empty");
                alert3.showAndWait();
            }





            }

        }



    @FXML
    void updatePatientBtn(ActionEvent event) throws IOException {

        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/updatepatient.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();



    }


}
