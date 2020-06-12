package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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


    String pname,pdob,pgender,paddress,pphone;
    int pid;

    @FXML
    void checkBtnClick(ActionEvent event) {
        pid=Integer.parseInt(patientId.getText());



    }

    @FXML
    void updateBtnClick(ActionEvent event) {

    }

}
