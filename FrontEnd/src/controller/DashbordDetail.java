package controller;

import GetterSetter.*;
import GetterSetter.Generalmedicine;
import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class DashbordDetail {

    @FXML
    private AnchorPane patent;

    TextField txtfield=new TextField();


    @FXML
    void allMedicineInfoClick(ActionEvent event) {


        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Medicine Details");
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));

        TableView<allMedicine> tableView=new TableView();

        TableColumn<allMedicine,Long> tableColumn=new TableColumn<>("ID");
        TableColumn<allMedicine,String> tableColumn1=new TableColumn<>("Medicine Name");
        TableColumn<allMedicine,Double> tableColumn2=new TableColumn<>("Medicine Price");
        TableColumn<allMedicine,Integer> tableColumn3=new TableColumn<>("Medicine Quantity");

        ObservableList<allMedicine> list= FXCollections.observableArrayList();

        tableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableView.getColumns().addAll(tableColumn,tableColumn1,tableColumn2,tableColumn3);

        Medicine[] m=Medicine.getAllMedicines();

        for( int i=0 ; i<m.length ; i++){

            list.add(new allMedicine(m[i].getId(),m[i].getMedicineName(),m[i].getPrice(),m[i].getQuantity()));
            tableView.setItems(list);
        }

        txtfield.setAlignment(Pos.CENTER);
        txtfield.setPromptText("Search");
        gridPane.add(txtfield,0,0);
        gridPane.add(tableView,0,1);

        FilteredList<allMedicine> filterdata=new FilteredList<>(list, p->true);
        txtfield.textProperty().addListener((observable,oldvalue,newvalue)->{
            filterdata.setPredicate(e->{
                if(newvalue==null || newvalue.isEmpty())
                {
                    return true;
                }
                String searchvalue=newvalue.toLowerCase();
                if(e.getName().toLowerCase().contains(searchvalue))
                {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<allMedicine> sorteddata=new SortedList<>(filterdata);
        sorteddata.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sorteddata);


        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
    }

    @FXML
    void allPatientInfo(ActionEvent event) {
        Patient[] p=Patient.getAllPatients();

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        GridPane gridPane=new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));

        TableView<AllPatientDetails> tableView=new TableView();

        TableColumn<AllPatientDetails,Long> tableColumn=new TableColumn<>("UHID");
        TableColumn<AllPatientDetails,String > tableColumn1=new TableColumn<>("NAME");
        TableColumn<AllPatientDetails,String> tableColumn2=new TableColumn<>("DOB");
        TableColumn<AllPatientDetails,String> tableColumn3=new TableColumn<>("GENDER");
        TableColumn<AllPatientDetails,String> tableColumn4=new TableColumn<>("ADDRESS");
        TableColumn<AllPatientDetails,Long> tableColumn5=new TableColumn<>("PHONE NUMBER");

        ObservableList<AllPatientDetails> list= FXCollections.observableArrayList();

        tableColumn.setCellValueFactory(new PropertyValueFactory<>("uhid"));
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn1);
        tableView.getColumns().add(tableColumn2);
        tableView.getColumns().add(tableColumn3);
        tableView.getColumns().add(tableColumn4);
        tableView.getColumns().add(tableColumn5);

        for (int i=0;i<p.length;i++){
            list.add(new AllPatientDetails(p[i].getUHID(),p[i].getPatientName(),p[i].getDOB(),p[i].getGender(),p[i].getAddress(),p[i].getPhoneNumber()));
            tableView.setItems(list);
        }

        txtfield.setAlignment(Pos.CENTER);
        txtfield.setPromptText("Search");
        gridPane.add(txtfield,0,0);
        gridPane.add(tableView,0,1);

        FilteredList<AllPatientDetails> filterdata=new FilteredList<>(list, p1->true);
        txtfield.textProperty().addListener((observable,oldvalue,newvalue)->{
            filterdata.setPredicate(e1->{
                if(newvalue==null || newvalue.isEmpty())
                {
                    return true;
                }
                String searchvalue=newvalue.toLowerCase();
                if(e1.getName().toLowerCase().contains(searchvalue))
                {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<AllPatientDetails> sorteddata=new SortedList<>(filterdata);
        sorteddata.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sorteddata);



        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();

    }

    @FXML
    void singlePatientInfo(ActionEvent event) throws Exception {

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Patient info");
            GridPane grid = new GridPane();
            TextField patientId=new TextField();
            Button button=new Button("Click");
            grid.add(patientId,0,0);
            grid.add(button,1,0);
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




        TableView<PreviousHistory> t1 = new TableView();
        t1.setPrefHeight(150);
        TableColumn<PreviousHistory,Long> tc1=new TableColumn<>("Examination id");
        TableColumn<PreviousHistory,Long> tc0=new TableColumn<>("Complaint  id");

        TableColumn<PreviousHistory,String> tc2=new TableColumn<>("BP");
        TableColumn<PreviousHistory,String> tc3=new TableColumn<>("PULSE");
        TableColumn<PreviousHistory,String> tc4=new TableColumn<>("TEMPERATURE");
        TableColumn<PreviousHistory,String> tc5=new TableColumn<>("CVS");
        TableColumn<PreviousHistory,String> tc6=new TableColumn<>("RS");
        TableColumn<PreviousHistory,String> tc7=new TableColumn<>("PA");
        TableColumn<PreviousHistory,String> tc8=new TableColumn<>("CNS");
        TableColumn<PreviousHistory,String> tc9=new TableColumn<>("LABTEST");
        TableColumn<PreviousHistory,String> tc10=new TableColumn<>("ECG");
        TableColumn<PreviousHistory,String> tc11=new TableColumn<>("X-RAY");
        TableColumn<PreviousHistory,String> tc12=new TableColumn<>("CT");
        TableColumn<PreviousHistory,String> tc13=new TableColumn<>("TWO_D_ECHO");
        TableColumn<PreviousHistory,String> tc14=new TableColumn<>("TMT");
        TableColumn<PreviousHistory,String> tc15=new TableColumn<>("EEG");
        TableColumn<PreviousHistory,String> tc16=new TableColumn<>("DIAGONISIS");
        TableColumn<PreviousHistory,String> tc17=new TableColumn<>("OTHER");




        TableView<PreviousHistory> t2=new TableView<>();
        t2.setPrefHeight(150);

        TableColumn<PreviousHistory,Long> tl1=new TableColumn<>("Prescription-complaint id");
        TableColumn<PreviousHistory,Long> tl2=new TableColumn<>("Prescription id");
        TableColumn<PreviousHistory,Long> tl3=new TableColumn<>("Medicine id");
        TableColumn<PreviousHistory,Integer> tl4=new TableColumn<>("Quantity");
        TableColumn<PreviousHistory,Boolean> tl5=new TableColumn<>("Morning");
        TableColumn<PreviousHistory,Boolean> tl6=new TableColumn<>("Afternoon");
        TableColumn<PreviousHistory,Boolean> tl7=new TableColumn<>("Night");



        button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    boolean b1= Pattern.matches("\\d*",patientId.getText());
                    if(b1==false)
                    {
                        Alert ab = new Alert(Alert.AlertType.INFORMATION);
                        ab.setTitle("Wrong Information");
                        ab.setContentText("Please enter numeric value");
                        ab.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        ab.showAndWait();
                    }
                    else {

                        CompleteDataForComplaint[] cd = new CompleteDataForComplaint[0];
                        try {
                            cd = CompleteDataForComplaint.getDataForPatientId(Long.parseLong(patientId.getText()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

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


                        for (int i = 0; i < cd.length; i++) {
                            ob.add(new PreviousHistory(cd[i].getComplaint().getId(), cd[i].getComplaint().getComplaint1(), cd[i].getComplaint().getComplaint2(), cd[i].getComplaint().getComplaint3(), cd[i].getComplaint().getExplanation1(), cd[i].getComplaint().getExplanation2(), cd[i].getComplaint().getExplanation3()));
                            tb.setItems(ob);
                        }


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


                        tl1.setCellValueFactory(new PropertyValueFactory<>("presecompid"));
                        tl2.setCellValueFactory(new PropertyValueFactory<>("presecid"));
                        tl3.setCellValueFactory(new PropertyValueFactory<>("medid"));
                        tl4.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                        tl5.setCellValueFactory(new PropertyValueFactory<>("morning"));
                        tl6.setCellValueFactory(new PropertyValueFactory<>("afternoon"));
                        tl7.setCellValueFactory(new PropertyValueFactory<>("night"));

                        t2.getColumns().addAll(tl1, tl2, tl3, tl4, tl5, tl6, tl7);

                        for (int i = 0; i < cd.length; i++) {
                            CompleteMedicinePrescription[] cm = cd[i].getCompleteMedicinePrescriptions();
                            for (int j = 0; j < cm.length; j++) {
                                ob2.add(new PreviousHistory(cm[j].getMedicinePrescription().getComplaintId(), cm[j].getMedicinePrescription().getId(), cm[j].getMedicinePrescription().getMedicineId(), cm[j].getMedicinePrescription().getQuantity(), cm[j].getMedicinePrescription().getMorning(), cm[j].getMedicinePrescription().getAfternoon(), cm[j].getMedicinePrescription().getNight()));
                                t2.setItems(ob2);
                            }

                        }
                    }


                }
            });


            Text tx=new Text("Complaints :");
            Text tx1=new Text("Examination :");
            Text tx2=new Text("Prescription :");

            grid.add(tx,0,1);
            grid.add(tb,0,2);
            grid.add(tx1,0,3);
            grid.add(t1,0,4);
            grid.add(tx2,0,5);
            grid.add(t2,0,6);

            a.getDialogPane().setContent(grid);

            grid.setPrefWidth(700);

            a.showAndWait();




    }

//*************************************************** NAV BAR *************************************************************************************************//


    @FXML
    void backClick(ActionEvent event) throws IOException {

        patent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordFront.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();



    }

    @FXML
    void clerkPageClick(ActionEvent event) throws IOException {
        patent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }

    @FXML
    void doctorPageClick(ActionEvent event) throws IOException {
        patent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }




}
