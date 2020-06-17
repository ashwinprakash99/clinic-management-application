package controller;

import dbConnector.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Updatepatient {

    @FXML
    private AnchorPane parent;


    @FXML
    private TextField patientId;

    @FXML
    private Button checkBtn;

    @FXML
    private TextField patientName;

    @FXML
    private DatePicker patientdob;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton others;

    @FXML
    private TextArea patientAddress;

    @FXML
    private TextField patientPhonenumber;


    String pname,pdob,pgender,paddress;
    Long pphone;
    int pid;

    @FXML
    void checkBtnClick(ActionEvent event) {

        if(patientId.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fields are empty !!!");
            alert.setContentText("Fields are empty please fill ");
            alert.showAndWait();
        }
        else {

            pid = Integer.parseInt(patientId.getText());
            boolean v = Patient.isPatientPresent(pid);
            if (v == true) {

                Patient p = Patient.getPatient(pid);
                patientName.setText(p.getPatientName());

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(p.getDOB(), dateTimeFormatter);
                patientdob.setValue(localDate);
                pgender = p.getGender();
                if (pgender.equals("M")) {
                    male.setSelected(true);
                } else if (pgender.equals("F")) {
                    female.setSelected(true);
                } else
                    others.setSelected(true);

                patientAddress.setText(p.getAddress());
                pphone = p.getPhoneNumber();
                patientPhonenumber.setText(pphone.toString());


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Patient not found");
                alert.setContentText("This patient is not present in db");
                alert.showAndWait();
            }
        }

    }


    @FXML
    void updateBtnClick(ActionEvent event) throws ParseException {


        if(patientId.getText().equals("") || patientName.getText().equals("") || patientAddress.getText().equals("") || patientPhonenumber.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fields are empty !!!");
            alert.setContentText("Fields are empty please fill ");
            alert.showAndWait();
        }
        else {


            LocalDate localDate = patientdob.getValue();
            pname = patientName.getText();
            if (male.isSelected()) {
                pgender = "M";
            } else if (female.isSelected()) {
                pgender = "F";
            } else {
                pgender = "O";
            }
            pdob = localDate.toString();
            paddress = patientAddress.getText();
            pphone = Long.parseLong(patientPhonenumber.getText());

            System.out.println(pname + " " + pdob + " " + pgender + " " + paddress + " " + pphone + " " + pid);

            Patient patient = new Patient();

            patient.setUHID(pid);
            patient.setPatientName(pname);
            patient.setDOB(pdob);
            patient.setGender(pgender);
            patient.setAddress(paddress);
            patient.setPhoneNumber(pphone);

            boolean v = Patient.updatePatient(patient);
            if (v == true) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Updated");
                alert.setContentText("Patient Details are updated");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setContentText("Patient Details are not updated !!!");
                alert.showAndWait();

            }
        }



    }

    public void backClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordPatient.fxml"));
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
