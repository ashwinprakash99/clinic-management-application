package controller;

import dbConnector.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddPatient implements Initializable {


    @FXML
    private AnchorPane parent;


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        LocalDate localDate=LocalDate.parse(modifiedDate,dateTimeFormatter);
        dob.setValue(localDate);

    }

    public void addPatientButtonClick(ActionEvent e) throws ParseException, IOException {
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
        boolean b1= Pattern.matches("\\d{10}",pphone);
        boolean b2=Pattern.matches("[a-zA-Z]+[ ]*[\\.\\-\\_]?[a-zA-Z]*[ ]*[\\.\\-\\_]?[a-zA-Z]*",pname);



        if(pname.equals("") || pdob.equals("") || pgender.equals("") || paddress.equals("") || pphone.equals("")){

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fields are empty !!!");
            alert.setContentText("Fields are empty please fill ");
            alert.showAndWait();
        }
        else {
            if (b1 == false || b2 == false) {
                Alert ab = new Alert(Alert.AlertType.INFORMATION);
                ab.setTitle("Wrong Information");
                ab.setContentText("Please enter 10 digit phone number or Check the name field");
                ab.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                ab.showAndWait();

            } else {
                Patient p = new Patient(pname, pdob, pgender, paddress, Long.parseLong(pphone));
                Long uhid = Patient.addPatient(p);
                if (uhid != -1) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Patient Added");
                    a.setContentText("Patient id: " + uhid);
                    a.showAndWait();

                    parent.getScene().getWindow().hide();
                    Stage dashbordClerk = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashbordPatient.fxml"));
                    Scene scene = new Scene(root);
                    dashbordClerk.setScene(scene);
                    dashbordClerk.show();


                } else {
                    Alert a = new Alert((Alert.AlertType.ERROR));
                    a.setTitle("Please Check the Patient details");
                    a.setContentText("Please recheck the patient details");
                    a.showAndWait();
                }
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

    public void doctorPageClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }
    public void allDetailsClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordDetail.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }
}
