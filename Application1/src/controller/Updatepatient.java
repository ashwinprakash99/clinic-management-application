package controller;

import dbConnector.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Updatepatient {
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
        pid=Integer.parseInt(patientId.getText());
       boolean v= Patient.isPatientPresent(pid);
       if (v==true){

           Patient p= Patient.getPatient(pid);
           patientName.setText(p.getPatientName());

           DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate localDate=LocalDate.parse(p.getDOB(),dateTimeFormatter);
           patientdob.setValue(localDate);
           pgender=p.getGender();
           if(pgender.equals("M")){
               male.setSelected(true);
           }
           else if(pgender.equals("F")){
               female.setSelected(true);
           }
           else
               others.setSelected(true);

           patientAddress.setText(p.getAddress());
           pphone=p.getPhoneNumber();
           patientPhonenumber.setText(pphone.toString());


       }
       else{
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Patient not found");
           alert.setContentText("This patient is not present in db");
           alert.showAndWait();
       }

    }


    @FXML
    void updateBtnClick(ActionEvent event) throws ParseException {
        LocalDate localDate=patientdob.getValue();
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
        pdob=localDate.toString();
        paddress=patientAddress.getText();
        pphone=Long.parseLong(patientPhonenumber.getText());

        System.out.println(pname+" "+pdob+" "+pgender+" "+paddress+" "+pphone+" "+pid);

          Patient patient=new Patient();
          patient.setUHID(pid);
          patient.setPatientName(pname);
          patient.setDOB(pdob);
          patient.setGender(pgender);
          patient.setAddress(paddress);
          patient.setPhoneNumber(pphone);
          Patient.updatePatient(patient);

          //Alert box pending


    }

}
