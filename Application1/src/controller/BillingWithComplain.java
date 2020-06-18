package controller;
import GetterSetter.*;
import dbConnector.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private TableColumn<GetterSetter.BillingWithComplain, Boolean> morn;

    @FXML
    private TableColumn<GetterSetter.BillingWithComplain, Boolean> after;

    @FXML
    private TableColumn<GetterSetter.BillingWithComplain, Boolean> nig;


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
            morn.setCellValueFactory(new PropertyValueFactory<>("morning"));
            after.setCellValueFactory(new PropertyValueFactory<>("Afternoon"));
            nig.setCellValueFactory(new PropertyValueFactory<>("night"));

            for(int i=0;i<cp.length;i++){
                list.add(new GetterSetter.BillingWithComplain((long) (i+1),cp[i].getMedicine().getMedicineName(),cp[i].getMedicinePrescription().getQuantity(),cp[i].getMedicinePrescription().getCost(),cp[i].getMedicinePrescription().getMorning(),cp[i].getMedicinePrescription().getAfternoon(),cp[i].getMedicinePrescription().getNight()));
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
        else if(cusultaion.getText().equals("") && !extra.getText().equals("")){
            consu=200+Integer.parseInt(extra.getText());

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
    void updateButon(ActionEvent event) throws Exception {

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        GridPane gridPane=new GridPane();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));

        TableView<updateMedicine> tableView=new TableView();

        TableColumn<updateMedicine,CheckBox> tableColumn=new TableColumn("Select");
        TableColumn<updateMedicine,String> tableColumn1=new TableColumn<>("Medicine Name");
        TableColumn<updateMedicine,TextField> tableColumn2=new TableColumn<>("Quantity");

        tableView.getColumns().addAll(tableColumn,tableColumn1,tableColumn2);

        CompleteMedicinePrescription[] cp=CompleteMedicinePrescription.getCompleteMedicineData(Long.parseLong(complaintId.getText()));

        ObservableList<updateMedicine> list= FXCollections.observableArrayList();
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("medName"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        for(int i=0;i<cp.length;i++){
            CheckBox select=new CheckBox(""+(i+1));
            TextField qnt=new TextField(""+cp[i].getMedicinePrescription().getQuantity());
            list.add(new updateMedicine(select,cp[i].getMedicine().getMedicineName(),qnt));
            tableView.setItems(list);

        }
        gridPane.add(tableView,0,0);
        alert.getDialogPane().setContent(gridPane);

        Optional<ButtonType> result=alert.showAndWait();

        if(result.get()==ButtonType.OK){

            for(int i=0;i<tableView.getItems().size();i++) {
                if (tableView.getItems().get(i).getSelect().isSelected()) {

                    MedicinePrescription mp=new MedicinePrescription();
                    mp.setId(cp[i].getMedicinePrescription().getId());
                    mp.setComplaintId(Long.parseLong(complaintId.getText()));
                    mp.setMedicineId(cp[i].getMedicinePrescription().getMedicineId());
                    mp.setQuantity(Integer.parseInt(tableView.getItems().get(i).getQuantity().getText()));
                    mp.setMorning(cp[i].getMedicinePrescription().getMorning());
                    mp.setAfternoon(cp[i].getMedicinePrescription().getAfternoon());
                    mp.setNight(cp[i].getMedicinePrescription().getNight());

                    boolean v=MedicinePrescription.updateMedicinePrescription(mp);


                }
            }
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
