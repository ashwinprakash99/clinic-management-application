package controller;

import dbConnector.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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
    private CheckBox morning;

    @FXML
    private CheckBox afternoon;

    @FXML
    private CheckBox night;











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
    void previousHistoryClick(ActionEvent event) {
        boolean v=Complaint.isComplaintPresent(Long.parseLong(patientId.getText()));

        if ( v == true ){
            Complaint[] c = Complaint.getComplaints(Long.parseLong(patientId.getText()));
            // COMPLAIN PREVIEW


        }
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
    }

    @FXML
    void presciptionDoneClick(ActionEvent event) {


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

        //if delete for complaint

    }
    public void clerkPageClickExamination(ActionEvent actionEvent) throws IOException {
        parent2.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

        //delete

    }

    public void allDetailClickExamination(ActionEvent actionEvent) throws IOException {
        parent2.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

        //delte

    }



    public void backClickPresciption(ActionEvent actionEvent) throws IOException {

        parent3.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/examination.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

        //delete examination

    }

    public void clerkPageClickPrescription(ActionEvent actionEvent) throws IOException {
        parent3.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

        //delte
    }

    public void allDetailClickPrescription(ActionEvent actionEvent) throws IOException {
        parent3.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

        //delte

    }






}




