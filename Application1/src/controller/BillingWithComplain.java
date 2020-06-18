package controller;
import dbConnector.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillingWithComplain {

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
    void checkComplaint(ActionEvent event)  {
        try {
            CompleteMedicinePrescription[] cp=CompleteMedicinePrescription.getCompleteMedicineData(Long.parseLong(complaintId.getText()));

            ObservableList<GetterSetter.BillingWithComplain> list = FXCollections.observableArrayList();

            tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tabMed.setCellValueFactory(new PropertyValueFactory<>("MedName"));
            tabQntty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            tabPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            for(int i=0;i<cp.length;i++){
                list.add(new GetterSetter.BillingWithComplain((long) (i+1),cp[i].getMedicine().getMedicineName(),cp[i].getMedicinePrescription().getQuantity(),cp[i].getMedicine().getPrice()));
                tableView.setItems(list);
            }

            

        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    @FXML
    void okButton(ActionEvent event) {

    }




}
