package controller;

import GetterSetter.PrescriptionOperation;
import GetterSetter.PreviousHistory;
import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private TextField filtertext;

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
            boolean b1= Pattern.matches("\\d*",patientId.getText());
            if(b1==false) {
                Alert ab = new Alert(Alert.AlertType.INFORMATION);
                ab.setTitle("Wrong Information");
                ab.setContentText("Please enter numeric value");
                ab.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                ab.showAndWait();
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

    }

    @FXML
    void previousHistoryClick(ActionEvent event) {
        try
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Patient info");
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(10, 10, 10, 10));
            TableView<PreviousHistory> tb = new TableView();
            tb.setPrefHeight(150);
            TableColumn<PreviousHistory, Long> tb1 = new TableColumn<>("complaint id");
            TableColumn<PreviousHistory, String> tb2 = new TableColumn<>("complaint 1");
            TableColumn<PreviousHistory, String> tb3 = new TableColumn<>("complaint 2");
            TableColumn<PreviousHistory, String> tb4 = new TableColumn<>("complaint 3");
            TableColumn<PreviousHistory, String> tb5 = new TableColumn<>("examination 1");
            TableColumn<PreviousHistory, String> tb6 = new TableColumn<>("examination 2");
            TableColumn<PreviousHistory, String> tb7 = new TableColumn<>("examination 3");

            tb1.setCellValueFactory(new PropertyValueFactory<>("compid"));
            tb2.setCellValueFactory(new PropertyValueFactory<>("comp1"));
            tb3.setCellValueFactory(new PropertyValueFactory<>("comp2"));
            tb4.setCellValueFactory(new PropertyValueFactory<>("comp3"));
            tb5.setCellValueFactory(new PropertyValueFactory<>("examin1"));
            tb6.setCellValueFactory(new PropertyValueFactory<>("examin2"));
            tb7.setCellValueFactory(new PropertyValueFactory<>("examin3"));
            tb.getColumns().addAll(tb1, tb2, tb3, tb4, tb5, tb6, tb7);

            ObservableList<PreviousHistory> ob = FXCollections.observableArrayList();

            ObservableList<PreviousHistory> ob1 = FXCollections.observableArrayList();
            ObservableList<PreviousHistory> ob2 = FXCollections.observableArrayList();

            CompleteDataForComplaint[] cd = CompleteDataForComplaint.getDataForPatientId(Long.parseLong(patientId.getText()));

            for (int i = 0; i < cd.length; i++) {
                ob.add(new PreviousHistory(cd[i].getComplaint().getId(), cd[i].getComplaint().getComplaint1(), cd[i].getComplaint().getComplaint2(), cd[i].getComplaint().getComplaint3(), cd[i].getComplaint().getExplanation1(), cd[i].getComplaint().getExplanation2(), cd[i].getComplaint().getExplanation3()));
                tb.setItems(ob);
            }


            TableView<PreviousHistory> t1 = new TableView();
            t1.setPrefHeight(150);
            TableColumn<PreviousHistory, Long> tc1 = new TableColumn<>("Examination id");
            TableColumn<PreviousHistory, Long> tc0 = new TableColumn<>("Complaint  id");

            TableColumn<PreviousHistory, String> tc2 = new TableColumn<>("BP");
            TableColumn<PreviousHistory, String> tc3 = new TableColumn<>("PULSE");
            TableColumn<PreviousHistory, String> tc4 = new TableColumn<>("TEMPERATURE");
            TableColumn<PreviousHistory, String> tc5 = new TableColumn<>("CVS");
            TableColumn<PreviousHistory, String> tc6 = new TableColumn<>("RS");
            TableColumn<PreviousHistory, String> tc7 = new TableColumn<>("PA");
            TableColumn<PreviousHistory, String> tc8 = new TableColumn<>("CNS");
            TableColumn<PreviousHistory, String> tc9 = new TableColumn<>("LABTEST");
            TableColumn<PreviousHistory, String> tc10 = new TableColumn<>("ECG");
            TableColumn<PreviousHistory, String> tc11 = new TableColumn<>("X-RAY");
            TableColumn<PreviousHistory, String> tc12 = new TableColumn<>("CT");
            TableColumn<PreviousHistory, String> tc13 = new TableColumn<>("TWO_D_ECHO");
            TableColumn<PreviousHistory, String> tc14 = new TableColumn<>("TMT");
            TableColumn<PreviousHistory, String> tc15 = new TableColumn<>("EEG");
            TableColumn<PreviousHistory, String> tc16 = new TableColumn<>("DIAGONISIS");
            TableColumn<PreviousHistory, String> tc17 = new TableColumn<>("OTHER");

            tc1.setCellValueFactory(new PropertyValueFactory<>("excompid"));
            tc0.setCellValueFactory(new PropertyValueFactory<>("examid"));
            tc2.setCellValueFactory(new PropertyValueFactory<>("bp"));
            tc3.setCellValueFactory(new PropertyValueFactory<>("pulse"));
            tc4.setCellValueFactory(new PropertyValueFactory<>("temp"));
            tc5.setCellValueFactory(new PropertyValueFactory<>("cvs"));
            tc6.setCellValueFactory(new PropertyValueFactory<>("rs"));
            tc7.setCellValueFactory(new PropertyValueFactory<>("pa"));
            tc8.setCellValueFactory(new PropertyValueFactory<>("cns"));
            tc9.setCellValueFactory(new PropertyValueFactory<>("lab"));
            tc10.setCellValueFactory(new PropertyValueFactory<>("ecg"));
            tc11.setCellValueFactory(new PropertyValueFactory<>("xray"));
            tc12.setCellValueFactory(new PropertyValueFactory<>("ct"));
            tc13.setCellValueFactory(new PropertyValueFactory<>("two"));
            tc14.setCellValueFactory(new PropertyValueFactory<>("tmt"));
            tc15.setCellValueFactory(new PropertyValueFactory<>("eeg"));
            tc16.setCellValueFactory(new PropertyValueFactory<>("diag"));
            tc17.setCellValueFactory(new PropertyValueFactory<>("other"));

            t1.getColumns().addAll(tc1, tc0, tc2, tc3, tc4, tc5, tc6, tc7, tc8, tc9, tc10, tc11, tc12, tc13, tc14, tc15, tc16, tc17);

            for (int i = 0; i < cd.length; i++) {
                ob1.add(new PreviousHistory(cd[i].getExamination().getComplaintId(), cd[i].getExamination().getId(), cd[i].getExamination().getBp(), cd[i].getExamination().getPulse(), cd[i].getExamination().getTemperature(), cd[i].getExamination().getCvs(), cd[i].getExamination().getRs(), cd[i].getExamination().getPa(), cd[i].getExamination().getCns(), cd[i].getExamination().getLabtest(), cd[i].getExamination().getEcg(), cd[i].getExamination().getXray(), cd[i].getExamination().getCtScanMri(), cd[i].getExamination().getTwoDEcho(), cd[i].getExamination().getTmt(), cd[i].getExamination().getEeg(), cd[i].getExamination().getDiagnosis(), cd[i].getExamination().getOther()));
                t1.setItems(ob1);
            }

            TableView<PreviousHistory> t2 = new TableView<>();
            t2.setPrefHeight(150);

            TableColumn<PreviousHistory, Long> tl1 = new TableColumn<>("Prescription-complaint id");
            TableColumn<PreviousHistory, Long> tl2 = new TableColumn<>("Prescription id");
            TableColumn<PreviousHistory, String> tl3 = new TableColumn<>("Medicine Name");
            TableColumn<PreviousHistory, Integer> tl4 = new TableColumn<>("Quantity");
            TableColumn<PreviousHistory, Boolean> tl5 = new TableColumn<>("Morning");
            TableColumn<PreviousHistory, Boolean> tl6 = new TableColumn<>("Afternoon");
            TableColumn<PreviousHistory, Boolean> tl7 = new TableColumn<>("Night");

            tl1.setCellValueFactory(new PropertyValueFactory<>("presecompid"));
            tl2.setCellValueFactory(new PropertyValueFactory<>("presecid"));
            tl3.setCellValueFactory(new PropertyValueFactory<>("medName"));
            tl4.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            tl5.setCellValueFactory(new PropertyValueFactory<>("morning"));
            tl6.setCellValueFactory(new PropertyValueFactory<>("afternoon"));
            tl7.setCellValueFactory(new PropertyValueFactory<>("night"));

            t2.getColumns().addAll(tl1, tl2, tl3, tl4, tl5, tl6, tl7);

            for (int i = 0; i < cd.length; i++) {
                CompleteMedicinePrescription[] cm = cd[i].getCompleteMedicinePrescriptions();
                for (int j = 0; j < cm.length; j++) {
                    ob2.add(new PreviousHistory(cm[j].getMedicinePrescription().getComplaintId(), cm[j].getMedicinePrescription().getId(), cm[j].getMedicine().getMedicineName(), cm[j].getMedicinePrescription().getQuantity(), cm[j].getMedicinePrescription().getMorning(), cm[j].getMedicinePrescription().getAfternoon(), cm[j].getMedicinePrescription().getNight()));
                    t2.setItems(ob2);
                }

            }


            Text tx = new Text("Complaints ");
            Text tx1 = new Text("Examination ");
            Text tx2 = new Text("Prescription ");
            tx.setUnderline(true);
            tx1.setUnderline(true);
            tx2.setUnderline(true);

            Patient patient=Patient.getPatient(Long.parseLong(patientId.getText()));

            Text tid=new Text("ID : "+patientId.getText());

            Text tname=new Text("Name : "+patient.getPatientName());

            grid.add(tid, 0, 0);
            grid.add(tname, 0, 1);
            grid.add(tx, 0, 2);
            grid.add(tb, 0, 3);
            grid.add(tx1, 0, 4);
            grid.add(t1, 0, 5);
            grid.add(tx2, 0, 6);
            grid.add(t2, 0, 7);

            a.getDialogPane().setContent(grid);

            grid.setPrefWidth(700);

            a.showAndWait();
        }
        catch (Exception e)
        {
            boolean b1= Pattern.matches("\\d*",patientId.getText());
            if(b1==false) {
                Alert ab = new Alert(Alert.AlertType.INFORMATION);
                ab.setTitle("Wrong Information");
                ab.setContentText("Please enter numeric value");
                ab.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                ab.showAndWait();
            }
            else
                System.out.println(e);
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
            doctorName.setText("Dr Ashok");
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
        FilteredList<PrescriptionOperation> filterdata=new FilteredList<>(list, p->true);
        filtertext.textProperty().addListener((observable,oldvalue,newvalue)->{
            filterdata.setPredicate(e->{
                if(newvalue==null || newvalue.isEmpty())
                {
                    return true;
                }
                String searchvalue=newvalue.toLowerCase();
                if(e.getPrescMedName().toLowerCase().contains(searchvalue))
                {
                    return true;
                }
                else if(e.getSelect().isSelected()) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<PrescriptionOperation> sorteddata=new SortedList<>(filterdata);
        sorteddata.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sorteddata);

    }





    @FXML
    void presciptionDoneClick(ActionEvent event) throws IOException {
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

                MedicinePrescription mp = new MedicinePrescription(complainId, Long.parseLong(tableView.getItems().get(i).getSelect().getText()), Integer.parseInt(tableView.getItems().get(i).getPresQuantity().getText()), morning, afernoon, night);
               // System.out.println(m[i].getId()+" "+Long.parseLong(tableView.getItems().get(i).getSelect().getText()));
                Long prescId = MedicinePrescription.addMedicinePrescription(mp);

                if (prescId == -1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error occured");
                    alert.setContentText("Not inserted");
                    alert.showAndWait();
                    break;
                }
            }

            }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Complaint ID");
                alert.setContentText("Complain ID : " + complainId);
                alert.showAndWait();
                parent3.getScene().getWindow().hide();
                Stage dashbordClerk=new Stage();
                Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
                Scene scene=new Scene(root);
                dashbordClerk.setScene(scene);
                dashbordClerk.show();

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
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordDetail.fxml"));
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




