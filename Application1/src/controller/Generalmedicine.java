package controller;


import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Generalmedicine {
    private AnchorPane parent1;
    @FXML
    private TextField patientName;
    @FXML
    private TableView<GetterSetter.Generalmedicine> tabview;

    @FXML
    private TableColumn<GetterSetter.Generalmedicine, CheckBox> select;

    @FXML
    private TableColumn<GetterSetter.Generalmedicine, String> medName;

    @FXML
    private TableColumn<GetterSetter.Generalmedicine, TextField> quantity;

    @FXML
    private TableColumn<GetterSetter.Generalmedicine, Double> cost;
    Long id;
    Medicine[] m = Medicine.getAllMedicines();


    @FXML
    void patientSubmitClick(ActionEvent event) {
        GeneralBilling gb = new GeneralBilling(patientName.getText());
        id = GeneralBilling.addGeneralBilling(gb);
        System.out.println(id);
        if (id == -1) {
            Alert a2 = new Alert(Alert.AlertType.INFORMATION);
            a2.setTitle("General billing");
            a2.setContentText("Data is not inserted");
            a2.showAndWait();
        }
        select.setCellValueFactory(new PropertyValueFactory<>("select"));
        medName.setCellValueFactory(new PropertyValueFactory<>("medName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));


        ObservableList<GetterSetter.Generalmedicine> ol = FXCollections.observableArrayList();

        for (int i = 0; i < m.length; i++) {
            CheckBox selct = new CheckBox("" + (i + 1));
            TextField tx1 = new TextField();
            ol.add(new GetterSetter.Generalmedicine(selct, m[i].getMedicineName(), tx1, m[i].getPrice()));
            tabview.setItems(ol);

        }


    }

    @FXML
    void generalMedicineClick(ActionEvent event) throws IOException {

        for (int i = 0; i < tabview.getItems().size(); i++) {
            if (tabview.getItems().get(i).getSelect().isSelected()) {
                GeneralMedicineOutlet gm = new GeneralMedicineOutlet(id, m[i].getId(), Integer.parseInt(tabview.getItems().get(i).getQuantity().getText()));
                Long billid = GeneralMedicineOutlet.addGeneralMedicineOutlet(gm);
                if (id == -1) {
                    Alert a3 = new Alert(Alert.AlertType.INFORMATION);
                    a3.setTitle("Some");
                    a3.setContentText("Id not found");
                    a3.showAndWait();
                }
                System.out.println("Success");

            }
        }
    }
}


