package controller;

import GetterSetter.*;
import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class DashbordDetail {
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

    }

    @FXML
    void singlePatientInfo(ActionEvent event) {

    }

}
