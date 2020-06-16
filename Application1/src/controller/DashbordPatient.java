package controller;

import GetterSetter.*;
import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    void forgetIdClick(ActionEvent event) {

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Forgot ID");

        GridPane gridPane=new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));

        TextField ptname=new TextField();
        ptname.setPromptText("Patient Name");

        Button click=new Button("CHECK");

        TableView<ForgotId> tableView=new TableView();

        TableColumn<ForgotId,Long> tableColumn=new TableColumn<>("Patient ID");
        TableColumn<ForgotId,String> tableColumn1=new TableColumn<>("Patient Name");
        TableColumn<ForgotId,Long> tableColumn2=new TableColumn<>("Patient Phone No");

        ObservableList<ForgotId> list= FXCollections.observableArrayList();

        tableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));

        tableView.getColumns().addAll(tableColumn,tableColumn1,tableColumn2);

        click.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Patient[] patient=Patient.getPatients(ptname.getText());

                for (int i=0;i<patient.length;i++){
                    list.add(new ForgotId(patient[i].getUHID(),patient[i].getPatientName(),patient[i].getPhoneNumber()));
                    tableView.setItems(list);
                }

            }
        });

        gridPane.add(ptname,0,0);
        gridPane.add(click,1,0);
        gridPane.add(tableView,0,1);

        alert.getDialogPane().setContent(gridPane);

        alert.showAndWait();



    }


    public void clerkPageClick(ActionEvent actionEvent) throws IOException {

    }

    public void doctorPageClick(ActionEvent actionEvent) {
    }

    public void backClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }
}
