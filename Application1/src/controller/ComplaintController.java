package controller;

import GetterSetter.PrescriptionOperation;
import GetterSetter.PreviousHistory;
import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ComplaintController {

   static Long complainId,examinationId;



    //****************************************  COMPLAINT   ******************************************************************************//

    @FXML
    private AnchorPane parent;

    @FXML
    private TextField patientId;

    @FXML
    private TextField complain1;

    @FXML
    private TextField complain2;

    @FXML
    private TextField complain3;

    @FXML
    private TextField examination1;

    @FXML
    private TextField examination2;

    @FXML
    private TextField examination3;
    //*********************************************************************************************************************************************************//

    //********************************************************************* EXAMINATION ***********************************************************************//


    @FXML
    private AnchorPane parent2;

    @FXML
    private TextArea bp;

    @FXML
    private TextArea pulse;

    @FXML
    private TextArea temp;

    @FXML
    private TextArea cvs;

    @FXML
    private TextArea ecg;

    @FXML
    private TextArea eeg;

    @FXML
    private TextArea tmt;

    @FXML
    private TextArea cns;

    @FXML
    private TextArea labtest;

    @FXML
    private TextArea xray;

    @FXML
    private TextArea ct;

    @FXML
    private TextArea two;

    @FXML
    private TextArea pa;

    @FXML
    private TextArea rs;

    @FXML
    private TextArea diag;

    @FXML
    private TextArea others;


    //******************************************************************************************************************************************************************//

    //************************************************************************** TREATMENT AND PRESCRIPTION ************************************************************//



    @FXML
    private AnchorPane parent3;

    @FXML
    private TextField doctorName;

    @FXML
    private TextArea treatmentDescription;

    @FXML
    private TableView<PrescriptionOperation> tableView;


    @FXML
    private TableColumn<PrescriptionOperation, CheckBox> select;

    @FXML
    private TableColumn<PrescriptionOperation, String> prescMedName;

    @FXML
    private TableColumn<PrescriptionOperation, Integer> prescStock;

    @FXML
    private TableColumn<PrescriptionOperation, TextField> prescQuantity;

    @FXML
    private TableColumn<PrescriptionOperation, Long> prescPrice;

    @FXML
    private TableColumn<PrescriptionOperation, CheckBox> prescMorning;

    @FXML
    private TableColumn<PrescriptionOperation, CheckBox> prescAfternoon;

    @FXML
    private TableColumn<PrescriptionOperation, CheckBox> prescNight;


    CheckBox selcts;



    //******************************************************









    //**************************************************************************************************************************************************************//

    //*************************************************************************  EVENT HANDLER  ********************************************************************//

    //**************************************************************************  COMPLAINT  *********************************************************************//
    @FXML
    void examinationClick(ActionEvent event) throws IOException {

        if(patientId.getText().equals("") || complain1.getText().equals("") || examination1.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setContentText("FIELDS ARE EMPTY PLEASE ENTER THE DETAILS!!!");
            alert.showAndWait();

        }
        else {

            Complaint complaint = new Complaint(Long.parseLong(patientId.getText()), complain1.getText(), complain2.getText(), complain3.getText(), examination1.getText(), examination2.getText(), examination3.getText());
            complainId = Complaint.addComplaint(complaint);
            if (complainId == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in complain");
                alert.setContentText(" COMPLAIN DID NOT REGISTER IN DATABASE");
                alert.showAndWait();
            } else {
                parent.getScene().getWindow().hide();
                Stage dashbordClerk = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/examination.fxml"));
                Scene scene = new Scene(root);
                dashbordClerk.setScene(scene);
                dashbordClerk.show();


            }
        }

    }

    @FXML
    void previousHistoryClick(ActionEvent event) throws Exception {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Patient info");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        TableView<PreviousHistory> tb = new TableView();
        TableColumn<PreviousHistory, Long> tb1 = new TableColumn<>("complaint id");
        TableColumn<PreviousHistory, Long> tb2 = new TableColumn<>("complaint 1");
        TableColumn<PreviousHistory, Long> tb3 = new TableColumn<>("complaint 2");
        TableColumn<PreviousHistory, Long> tb4 = new TableColumn<>("complaint 3");
        TableColumn<PreviousHistory, Long> tb5 = new TableColumn<>("examination 1");
        TableColumn<PreviousHistory, Long> tb6 = new TableColumn<>("examination 2");
        TableColumn<PreviousHistory, Long> tb7 = new TableColumn<>("examination 3");

        tb1.setCellValueFactory(new PropertyValueFactory<>("compid"));
        tb2.setCellValueFactory(new PropertyValueFactory<>("comp1"));
        tb3.setCellValueFactory(new PropertyValueFactory<>("comp2"));
        tb4.setCellValueFactory(new PropertyValueFactory<>("comp3"));
        tb5.setCellValueFactory(new PropertyValueFactory<>("examin1"));
        tb6.setCellValueFactory(new PropertyValueFactory<>("examin2"));
        tb7.setCellValueFactory(new PropertyValueFactory<>("examin3"));
        tb.getColumns().addAll(tb1,tb2,tb3,tb4,tb5,tb6,tb7);

        ObservableList<PreviousHistory> ob = FXCollections.observableArrayList();

            CompleteDataForComplaint[] cd = CompleteDataForComplaint.getDataForPatientId(Long.parseLong(patientId.getText()));
            System.out.println(patientId.getText());
            for (int i=0;i<cd.length;i++)
            {
                ob.add(new PreviousHistory(cd[i].getComplaint().getId(),cd[i].getComplaint().getComplaint1(),cd[i].getComplaint().getComplaint2(),cd[i].getComplaint().getComplaint3(),cd[i].getComplaint().getExplanation1(),cd[i].getComplaint().getExplanation2(),cd[i].getComplaint().getExplanation3()));
                tb.setItems(ob);
            }


        grid.add(tb,0,0);
        a.getDialogPane().setContent(grid);
        a.showAndWait();
    }
    //**********************************************************************************************************************************************************//

    //************************************************************************  EXAMINATION ********************************************************************//


    @FXML
    void nextClick(ActionEvent event) throws IOException {
        Examination examination=new Examination(complainId,bp.getText(),pulse.getText(),temp.getText(),cvs.getText(),rs.getText(),pa.getText(),cns.getText(),labtest.getText(),ecg.getText(),xray.getText(),ct.getText(),two.getText(),tmt.getText(),eeg.getText(),diag.getText(),others.getText());
        examinationId= Examination.addExamination(examination);

        if (examinationId == -1){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR IN DATABASE");
            alert.setContentText("EXAMINATION DID NOT REGISTER IN DATABASE");
            alert.showAndWait();
        }
        else{

            parent2.getScene().getWindow().hide();
            Stage dashbordClerk=new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("/fxml/treatmentAndpresciption.fxml"));
            Scene scene=new Scene(root);
            dashbordClerk.setScene(scene);
            dashbordClerk.show();

        }

    }
    //********************************************************************************************************************************************************************//

    //************************************************************************** TREATMENT AND PRESCRIPTION ************************************************************//

    @FXML
    void enterTreatmentClick(ActionEvent event) {

        if (doctorName.getText().equals("")){
            doctorName.setText("Default");
        }
        if(treatmentDescription.getText().equals("")){
            treatmentDescription.setText("Everything is good, Please take some rest !!");
        }

        Treatment treatment=new Treatment(doctorName.getText(),treatmentDescription.getText(),complainId);


        Long tid=Treatment.addTreatment(treatment);

        if (tid== -1){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR IN DATABASE");
            alert.setContentText("TREATMENT DID NOT REGISTER IN DATABASE");
            alert.showAndWait();
        }


        ObservableList<PrescriptionOperation> list= FXCollections.observableArrayList();

        Medicine[] m=Medicine.getAllMedicines();

        select.setCellValueFactory(new PropertyValueFactory<>("select"));
        prescMedName.setCellValueFactory(new PropertyValueFactory<>("prescMedName"));
        prescStock.setCellValueFactory(new PropertyValueFactory<>("prescStock"));
        prescQuantity.setCellValueFactory(new PropertyValueFactory<>("presQuantity"));
        prescPrice.setCellValueFactory(new PropertyValueFactory<>("prescPrice"));
        prescMorning.setCellValueFactory(new PropertyValueFactory<>("morning"));
        prescAfternoon.setCellValueFactory(new PropertyValueFactory<>("afternoon"));
        prescNight.setCellValueFactory(new PropertyValueFactory<>("evening"));


        for (int i=0;i<m.length;i++) {

            selcts = new CheckBox(""+(i+1));
            CheckBox morn = new CheckBox(null);
            CheckBox after = new CheckBox(null);
            CheckBox nig = new CheckBox(null);
            TextField geQuantity = new TextField();

            list.add(new PrescriptionOperation(selcts,m[i].getMedicineName(),m[i].getQuantity(),geQuantity,m[i].getPrice(),morn,after,nig));
            tableView.setItems(list);
        }

    }



    @FXML
    void presciptionDoneClick(ActionEvent event) {
        Medicine[] m=Medicine.getAllMedicines();
        if(tableView.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Complaint ID");
            alert.setContentText("Complain ID : " + complainId);
            alert.showAndWait();

        }
        else{

        for(int i=0;i<tableView.getItems().size();i++){
            if(tableView.getItems().get(i).getSelect().isSelected()) {
                boolean morning = false;
                boolean afernoon = false;
                boolean night = false;
                if (tableView.getItems().get(i).getMorning().isSelected()) {
                    morning = true;
                }
                if (tableView.getItems().get(i).getAfternoon().isSelected()) {
                    afernoon = true;
                }
                if (tableView.getItems().get(i).getEvening().isSelected()) {
                    night = true;
                }

                MedicinePrescription mp = new MedicinePrescription(complainId, m[i].getId(), Integer.parseInt(tableView.getItems().get(i).getPresQuantity().getText()), morning, afernoon, night);
                Long prescId = MedicinePrescription.addMedicinePrescription(mp);

                if (prescId == -1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error occured");
                    alert.setContentText("Not inserted");
                    alert.showAndWait();
                    break;
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Complaint ID");
                    alert.setContentText("Complain ID : " + complainId);
                    alert.showAndWait();
                }
            }

            }
        }

    }

    //***************************************************************************************************************************************************************//

    public void backClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordFront.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }

    public void clerkPageClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }

    public void allDetailClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }


    public void backClickExamination(ActionEvent actionEvent) throws IOException {
        parent2.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();



    }
    public void clerkPageClickExamination(ActionEvent actionEvent) throws IOException {
        parent2.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();



    }

    public void allDetailClickExamination(ActionEvent actionEvent) throws IOException {
        parent2.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }



    public void backClickPresciption(ActionEvent actionEvent) throws IOException {

        parent3.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/examination.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();


    }

    public void clerkPageClickPrescription(ActionEvent actionEvent) throws IOException {
        parent3.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }

    public void allDetailClickPrescription(ActionEvent actionEvent) throws IOException {
        parent3.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();


    }



}




