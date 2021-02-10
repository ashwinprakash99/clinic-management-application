package controller;

import GetterSetter.BillingDetails;
import dbConnector.Billing;
import dbConnector.CompleteDataForComplaint;
import dbConnector.CompleteGeneralBillingData;
import dbConnector.GeneralBilling;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashbordFront {

    @FXML
    private AnchorPane dashbordfront;


    @FXML
    public void clerkClick(ActionEvent e ) throws Exception{

        dashbordfront.getScene().getWindow().hide();

        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }

    @FXML
    public void doctorClick(ActionEvent e ) throws Exception{

        dashbordfront.getScene().getWindow().hide();

        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }

    @FXML
    public void detailClick(ActionEvent e ) throws Exception{

        dashbordfront.getScene().getWindow().hide();

        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordDetail.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }

    public void todaysInfo(ActionEvent actionEvent) {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        LocalDate localDate=LocalDate.parse(modifiedDate,dateTimeFormatter);
       // System.out.println(localDate);

        Double btotal=Billing.getBillTotalBetweenDates(localDate.toString(),localDate.toString());
        Double genTotal= GeneralBilling.getGeneralBillTotalBetweenDates(localDate.toString(),localDate.toString());
        Double total=btotal+genTotal;
//        System.out.println(total);


        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Total amount");


        CompleteDataForComplaint[] cc=CompleteDataForComplaint.getDataBetweenDates(localDate.toString(),localDate.toString());

        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        TableView<BillingDetails> tableView=new TableView<>();
        TableColumn<BillingDetails,Long> tc1=new TableColumn<>("Billing ID");
        TableColumn<BillingDetails,Long> tc2=new TableColumn<>("Compain ID");
        TableColumn<BillingDetails,Double> tc3=new TableColumn<>("Consultation fees ");
        TableColumn<BillingDetails,Double> tc4=new TableColumn<>("Total Med fees ");

        tc1.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("CompId"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("consultation"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("totfee"));

        tableView.getColumns().addAll(tc1,tc2,tc3,tc4);

        ObservableList<BillingDetails> list = FXCollections.observableArrayList();
        for(int i=0;i<cc.length;i++){
            list.add(new BillingDetails(cc[i].getBilling().getId(),cc[i].getBilling().getComplaintId(),cc[i].getBilling().getConsultationFee(),cc[i].getBilling().getTotalFee()));
            tableView.setItems(list);
        }


        Text stotal=new Text("Todays Total cost : "+total);
        Text billing=new Text("BILLING OF TODAY");

        gridPane.add(stotal,0,0);
        gridPane.add(billing,0,1);
        gridPane.add(tableView,0,2);
        alert.getDialogPane().setContent(gridPane);


        alert.showAndWait();


    }
}
