package controller;

import dbConnector.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class DashbordMedicine {

        @FXML
        private Button addMedicineBtn;

        @FXML
        private Button deleteMedicineBtn;

        @FXML
        private Button updateMedicineBtn;




        @FXML
        void addMedicineClick(ActionEvent event) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADD PATIENT ");
                GridPane gridPane=new GridPane();
                gridPane.setVgap(50);
                gridPane.setHgap(50);
                gridPane.setPadding(new Insets(20,20,20,20));
                TextField medicineName=new TextField(),medPrice=new TextField(),tax=new TextField(),quantity=new TextField();
                medicineName.setPromptText("Medicine Name");
                medPrice.setPromptText("Medicine Price");
                tax.setPromptText("Tax");
                quantity.setPromptText("Date");
                gridPane.add(medicineName,0,0);
                gridPane.add(medPrice,1,0);
                gridPane.add(tax,1,0);
                gridPane.add(quantity,1,1);
                alert.getDialogPane().setContent(gridPane);
                Optional<ButtonType> result=alert.showAndWait();

                if( result.get() == ButtonType.OK){
                        Medicine medicine=new Medicine(medicineName.getText(),Double.parseDouble(medPrice.getText()),Double.parseDouble(tax.getText()),Integer.parseInt(quantity.getText()));
                        Long medId=Medicine.addMedicine(medicine);
                        if(medId == -1){
                                //check

                        }
                }


        }

        @FXML
        void deleteMedicineClick(ActionEvent event) {

        }

        @FXML
        void updateMedicineClick(ActionEvent event) {

        }





}
