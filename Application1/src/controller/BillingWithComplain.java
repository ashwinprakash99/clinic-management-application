package controller;
import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BillingWithComplain implements Initializable {

    @FXML
    private AnchorPane parent;

    @FXML
    private TextField complaintId;

    @FXML
    private TableView<GetterSetter.BillingWithComplain> tableView;

    @FXML
    private TableColumn<GetterSetter.BillingWithComplain, Long> tabId;

    @FXML
    private TableColumn<GetterSetter.BillingWithComplain, String> tabMed;

    @FXML
    private TableColumn<GetterSetter.BillingWithComplain, Integer> tabQntty;

    @FXML
    private TableColumn<GetterSetter.BillingWithComplain, Double> tabPrice;

    @FXML
    private TextField cusultaion;

    @FXML
    private TextField extra;

    @FXML
    private HBox feesBox;

    @FXML
    private Text totalFess;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        feesBox.setVisible(false);
    }


    @FXML
    void checkComplaint(ActionEvent event)  {
        try {
            CompleteMedicinePrescription[] cp=CompleteMedicinePrescription.getCompleteMedicineData(Long.parseLong(complaintId.getText()));

            ObservableList<GetterSetter.BillingWithComplain> list = FXCollections.observableArrayList();

            tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tabMed.setCellValueFactory(new PropertyValueFactory<>("MedName"));
            tabQntty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            tabPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            for(int i=0;i<cp.length;i++){
                list.add(new GetterSetter.BillingWithComplain((long) (i+1),cp[i].getMedicine().getMedicineName(),cp[i].getMedicinePrescription().getQuantity(),cp[i].getMedicinePrescription().getCost()));
                tableView.setItems(list);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    @FXML
    void okButton(ActionEvent event) {

        int consu;
        if(extra.getText().equals("") && cusultaion.getText().equals("")){
            consu=200;
        }
        else if(extra.getText().equals("")){
            consu=Integer.parseInt(cusultaion.getText());
        }
        else{
            consu=Integer.parseInt(cusultaion.getText())+Integer.parseInt(extra.getText());
        }

        Billing billing=new Billing(Long.parseLong(complaintId.getText()),consu);
        Long id=Billing.addBilling(billing);
        if(id==-1){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error in adding billing");
            alert.showAndWait();
        }
        else{
            feesBox.setVisible(true);
            totalFess.setText(""+billing.getTotalFee());
        }



    }

    @FXML
    void home(ActionEvent event) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }


    public void backClick(ActionEvent actionEvent) throws IOException {
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

    public void doctorPageClick(ActionEvent actionEvent) throws IOException {
        parent.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/complaint.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();
    }
}
