package controller;

import dbConnector.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.time.LocalDate;

public class AddPatient {

    @FXML
    private TextField patientName;

    @FXML
    private DatePicker dob;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton other;

    @FXML
    private TextArea patientAddress;

    @FXML
    private TextField patientPhoneNumber;



    String pname,pdob,pgender,paddress,pphone;

    public void addPatientButtonClick(ActionEvent e) throws ParseException {

        LocalDate date = dob.getValue();
        pname=patientName.getText();

        if(male.isSelected()){
            pgender="M";
        }
        else if(female.isSelected()){
            pgender="F";
        }
        else{
            pgender="O";
        }

        pdob=date.toString();
        paddress=patientAddress.getText();
        pphone=patientPhoneNumber.getText();


        Patient p=new Patient(pname,pdob,pgender,paddress,Long.parseLong(pphone));
        Long uhid=Patient.addPatient(p);
        if(uhid != -1){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Patient Added");
            a.setContentText("Patient id: "+uhid);
            a.showAndWait();
        }
        else{
            Alert a=new Alert((Alert.AlertType.ERROR));
            a.setTitle("Please Check the Patient details");
            a.setContentText("Please recheck the patient details");
            a.showAndWait();
        }




    }



}
