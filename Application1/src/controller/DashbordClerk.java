package controller;

import dbConnector.CompleteMedicinePrescription;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

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


        Alert alert=new Alert(Alert.AlertType.NONE);

        alert.setTitle("Select");

        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));
        Button bill=new Button("Bill Directly"),forgetBill=new Button("Forget Id");
        alert.setHeight(500);
        alert.setWidth(300);
        bill.setPrefSize(100,100);
        forgetBill.setPrefSize(100,100);

        bill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                alert.hide();

                parent.getScene().getWindow().hide();
                Stage dashbordClerk=new Stage();
                Parent root= null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/billingWithComplain.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Scene scene=new Scene(root);
                dashbordClerk.setScene(scene);
                dashbordClerk.show();





            }
        });
        forgetBill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            //forget id
            }
        });

        gridPane.add(bill,0,0);
        gridPane.add(forgetBill,1,0);

        alert.getDialogPane().setContent(gridPane);

        alert.showAndWait();



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

    public void allDetails(ActionEvent actionEvent) throws IOException {

        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordDetail.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }

    public void doctorPageClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }
}
