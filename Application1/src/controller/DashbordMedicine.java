package controller;

import dbConnector.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DashbordMedicine {

        @FXML
        private Button addMedicineBtn;

        @FXML
        private Button deleteMedicineBtn;

        @FXML
        private Button updateMedicineBtn;
        @FXML
        private AnchorPane parent;
        Long id;



        Long addId;
        String addName;
        double addPrice,addTax;
        int addQnty;

        @FXML
        void addMedicineClick(ActionEvent event) throws IOException {
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
                quantity.setPromptText("Quantity");
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
                                Alert alert1=new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("Medicine Not added");
                                alert1.setContentText("Medicine is not added in the database!!");
                                alert1.showAndWait();

                        }
                        else{
                                parent.getScene().getWindow().hide();
                                Stage dashbordClerk=new Stage();
                                Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordMedicine.fxml"));
                                Scene scene=new Scene(root);
                                dashbordClerk.setScene(scene);
                                dashbordClerk.show();
                        }
                }


        }

        @FXML
        void deleteMedicineClick(ActionEvent event) throws IOException {

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Medicine");
                GridPane gridPane=new GridPane();
                gridPane.setHgap(50);
                gridPane.setVgap(50);
                gridPane.setPadding(new Insets(20,20,20,20));
                TextField medId=new TextField();
                medId.setPromptText("Medicine Id");
                gridPane.add(medId,0,0);
                alert.getDialogPane().setContent(gridPane);
                Optional<ButtonType> result=alert.showAndWait();

                if (result.get()==ButtonType.OK){
                        boolean v=Medicine.removeMedicine(Long.parseLong(medId.getText()));

                        if (v==false){
                                Alert alert1=new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("Delete Error");
                                alert1.setContentText("Error in deleting the Medicine!!");
                                alert1.showAndWait();
                        }
                        else{
                                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                                alert1.setTitle("Medicine Deleted");
                                alert1.setContentText("Successfully Deleted Medicine!!");
                                alert1.showAndWait();
                                parent.getScene().getWindow().hide();
                                Stage dashbordClerk=new Stage();
                                Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordMedicine.fxml"));
                                Scene scene=new Scene(root);
                                dashbordClerk.setScene(scene);
                                dashbordClerk.show();

                        }
                }

        }

        @FXML
        void updateMedicineClick(ActionEvent event) throws IOException {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Medicine");
                GridPane gridPane=new GridPane();
                gridPane.setHgap(50);
                gridPane.setVgap(50);
                gridPane.setPadding(new Insets(20,20,20,20));
                TextField medId=new TextField(),medName=new TextField(),tax=new TextField(),quantity=new TextField(),price=new TextField();



                medId.setPromptText("Medicine Id");
                medName.setPromptText("Medicine Name");
                price.setPromptText("Price");
                tax.setPromptText("Tax");
                quantity.setPromptText("Quantity");

                Button checkMed=new Button("Check Medicine");

                gridPane.add(medId,0,0);
                gridPane.add(checkMed,1,0);
                gridPane.add(medName,0,1);
                gridPane.add(price,1,1);
                gridPane.add(tax,0,2);
                gridPane.add(quantity,1,2);
                alert.getDialogPane().setContent(gridPane);


                checkMed.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                                boolean v=Medicine.isMedicinePresent(Long.parseLong(medId.getText()));
                                id=Long.parseLong(medId.getText());
                                if (v==false){
                                        Alert alert1=new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("Not Present");
                                        alert1.setContentText("Medicine Not present");
                                        alert1.showAndWait();
                                }
                                else{
                                        Medicine m=Medicine.getMedicine(Long.parseLong(medId.getText()));
                                        medName.setText(m.getMedicineName());
                                        price.setText(""+m.getPrice());
                                        tax.setText(""+m.getTax());
                                        quantity.setText(""+m.getQuantity());

                                }
                        }
                });

                Optional<ButtonType> result=alert.showAndWait();

                if (result.get()==ButtonType.OK){
                        Medicine medicine=new Medicine();
                        medicine.setId(id);
                        medicine.setMedicineName(medName.getText());
                        medicine.setPrice(Double.parseDouble(price.getText()));
                        medicine.setTax(Double.parseDouble(tax.getText()));
                        medicine.setQuantity(Integer.parseInt(quantity.getText()));

                        boolean v=Medicine.updateMedicine(medicine);

                        if(v == false){

                                Alert alert1=new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("Update Error");
                                alert1.setContentText("Could Not update");
                                alert1.showAndWait();
                        }
                        else{
                                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                                alert1.setTitle("Medicine Updated");
                                alert1.setContentText("Successfully Updated Medicine!!");
                                alert1.showAndWait();
                                parent.getScene().getWindow().hide();
                                Stage dashbordClerk=new Stage();
                                Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordMedicine.fxml"));
                                Scene scene=new Scene(root);
                                dashbordClerk.setScene(scene);
                                dashbordClerk.show();

                        }

                }

        }


        @FXML
        void buyMedicineClick(ActionEvent event) throws IOException {

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Medicine");
                GridPane gridPane=new GridPane();
                gridPane.setVgap(50);
                gridPane.setHgap(50);
                gridPane.setPadding(new Insets(20,20,20,20));

                TextField medId=new TextField(),quantity=new TextField();
                Button check=new Button("Check");
                Text medName=new Text(),qnty=new Text();

                medId.setPromptText("Medicine ID");
                quantity.setPromptText("Addition Quantity");
                medName.setText("MedName");
                qnty.setText("Quantity");

                gridPane.add(medId,0,0);
                gridPane.add(check,1,0);
                gridPane.add(medName,0,1);
                gridPane.add(qnty,1,1);
                gridPane.add(quantity,0,2);
                alert.getDialogPane().setContent(gridPane);

                check.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                                boolean v=Medicine.isMedicinePresent(Long.parseLong(medId.getText()));
                                addId=Long.parseLong(medId.getText());
                                if (v==false){
                                        Alert alert1=new Alert(Alert.AlertType.ERROR);
                                        alert1.setTitle("Not Present");
                                        alert1.setContentText("Medicine Not present");
                                        alert1.showAndWait();

                                }
                                else {
                                        Medicine m=Medicine.getMedicine(Long.parseLong(medId.getText()));
                                        addName=m.getMedicineName();
                                        addPrice=m.getPrice();
                                        addTax=m.getTax();
                                        addQnty=m.getQuantity();

                                        medName.setText(addName);
                                        qnty.setText(""+addQnty);

                                }

                        }
                });

                Optional<ButtonType> result=alert.showAndWait();

                if (result.get()==ButtonType.OK){
                        addQnty=addQnty+Integer.parseInt(quantity.getText());
                        System.out.println(addQnty);
                        Medicine medicine=new Medicine();
                        medicine.setId(addId);
                        medicine.setMedicineName(addName);
                        medicine.setPrice(addPrice);
                        medicine.setTax(addTax);
                        medicine.setQuantity(addQnty);

                        boolean v=Medicine.updateMedicine(medicine);

                        if(v == false){

                                Alert alert1=new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("Update Error Error");
                                alert1.setContentText("Could Not update");
                                alert1.showAndWait();
                        }
                        else{
                                Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                                alert1.setTitle("Medicine updated");
                                alert1.setContentText("Successfully Updated Medicine!!");
                                alert1.showAndWait();
                                parent.getScene().getWindow().hide();
                                Stage dashbordClerk=new Stage();
                                Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordMedicine.fxml"));
                                Scene scene=new Scene(root);
                                dashbordClerk.setScene(scene);
                                dashbordClerk.show();

                        }
                }
        }

        @FXML
        void checkMedicineClick(ActionEvent event) {
        //display the medicine lower than 10
        }





}
