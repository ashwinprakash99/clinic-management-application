package controller;

import GetterSetter.*;
import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.AmbientLight;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashbordDetail {

    @FXML
    private AnchorPane patent;



    @FXML
    void allMedicineInfoClick(ActionEvent event) {

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Medicine Details");
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));

        TableView<allMedicine> tableView=new TableView();

        TableColumn<allMedicine,Long> tableColumn=new TableColumn<>("ID");
        TableColumn<allMedicine,String> tableColumn1=new TableColumn<>("Medicine Name");
        TableColumn<allMedicine,Double> tableColumn2=new TableColumn<>("Medicine Price");
        TableColumn<allMedicine,Integer> tableColumn3=new TableColumn<>("Medicine Quantity");

        ObservableList<allMedicine> list= FXCollections.observableArrayList();

        tableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableView.getColumns().addAll(tableColumn,tableColumn1,tableColumn2,tableColumn3);

        Medicine[] m=Medicine.getAllMedicines();

        for( int i=0 ; i<m.length ; i++){

            list.add(new allMedicine(m[i].getId(),m[i].getMedicineName(),m[i].getPrice(),m[i].getQuantity()));
            tableView.setItems(list);

        }

        gridPane.add(tableView,0,0);
        alert.getDialogPane().setContent(gridPane);

        alert.showAndWait();







    }

    @FXML
    void allPatientInfo(ActionEvent event) {
        Patient[] p=Patient.getAllPatients();

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        GridPane gridPane=new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));

        TableView<AllPatientDetails> tableView=new TableView();

        TableColumn<AllPatientDetails,Long> tableColumn=new TableColumn<>("UHID");
        TableColumn<AllPatientDetails,String > tableColumn1=new TableColumn<>("NAME");
        TableColumn<AllPatientDetails,String> tableColumn2=new TableColumn<>("DOB");
        TableColumn<AllPatientDetails,String> tableColumn3=new TableColumn<>("GENDER");
        TableColumn<AllPatientDetails,String> tableColumn4=new TableColumn<>("ADDRESS");
        TableColumn<AllPatientDetails,Long> tableColumn5=new TableColumn<>("PHONE NUMBER");

        ObservableList<AllPatientDetails> list= FXCollections.observableArrayList();

        tableColumn.setCellValueFactory(new PropertyValueFactory<>("uhid"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableView.getColumns().addAll(tableColumn,tableColumn1,tableColumn2,tableColumn3,tableColumn4,tableColumn5);

        for (int i=0;i<p.length;i++){
            list.add(new AllPatientDetails(p[i].getUHID(),p[i].getPatientName(),p[i].getDOB(),p[i].getGender(),p[i].getAddress(),p[i].getPhoneNumber()));
            tableView.setItems(list);

        }
        gridPane.add(tableView,0,0);
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();








    }

    @FXML
    void singlePatientInfo(ActionEvent event) {

    }

//*************************************************** NAV BAR *************************************************************************************************//


    @FXML
    void backClick(ActionEvent event) throws IOException {

        patent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordFront.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();



    }

    @FXML
    void clerkPageClick(ActionEvent event) throws IOException {
        patent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }

    @FXML
    void doctorPageClick(ActionEvent event) throws IOException {
        patent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }




}
