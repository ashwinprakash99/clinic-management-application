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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Generalmedicine implements Initializable  {

    @FXML
    private AnchorPane patene;
    @FXML
    private HBox hbox;
    @FXML
    private Text tv;

    @FXML
    private TextField patientName;

    @FXML
    private TableView<GetterSetter.Generalmedicine> tabview;

    @FXML
    private TableColumn<GetterSetter.Generalmedicine, CheckBox> select;

    @FXML
    private TableColumn<GetterSetter.Generalmedicine, String> medName;

    @FXML
    private TableColumn<GetterSetter.Generalmedicine, Integer> stock;

    @FXML
    private Button submitBtn;



    @FXML
    private TableColumn<GetterSetter.Generalmedicine, TextField> quantity;

    @FXML
    private TableColumn<GetterSetter.Generalmedicine, Double> cost;

    Long id;
    Double sum=0.0;
    Medicine[] m = Medicine.getAllMedicines();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbox.setVisible(false);
    }


    @FXML
    void patientSubmitClick(ActionEvent event) {
        boolean b1= Pattern.matches("[a-zA-Z]+[ ]*[\\.\\-\\_]?[a-zA-Z]*[ ]*[\\.\\-\\_]?[a-zA-Z]*",patientName.getText());
        if(b1==false)
        {
            Alert ab = new Alert(Alert.AlertType.INFORMATION);
            ab.setTitle("Wrong Information");
            ab.setContentText("Please enter valid patient name");
            ab.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            ab.showAndWait();

        }
        else {
            GeneralBilling gb = new GeneralBilling(patientName.getText());
            id = GeneralBilling.addGeneralBilling(gb);
            System.out.println(id);
            if (id == -1) {
                Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                a2.setTitle("General billing");
                a2.setContentText("Data is not inserted");
                a2.showAndWait();
            }
            select.setCellValueFactory(new PropertyValueFactory<>("select"));
            medName.setCellValueFactory(new PropertyValueFactory<>("medName"));
            stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            cost.setCellValueFactory(new PropertyValueFactory<>("cost"));


            ObservableList<GetterSetter.Generalmedicine> ol = FXCollections.observableArrayList();

            for (int i = 0; i < m.length; i++) {
                CheckBox selct = new CheckBox("" + (i + 1));
                TextField tx1 = new TextField();
                ol.add(new GetterSetter.Generalmedicine(selct, m[i].getMedicineName(), m[i].getQuantity(), tx1, m[i].getPrice()));
                tabview.setItems(ol);

            }
        }


    }

    @FXML
    void generalMedicineClick(ActionEvent event) throws IOException {

        for (int i = 0; i < tabview.getItems().size(); i++) {
            if (tabview.getItems().get(i).getSelect().isSelected()) {
                GeneralMedicineOutlet gm = new GeneralMedicineOutlet(id, m[i].getId(), Integer.parseInt(tabview.getItems().get(i).getQuantity().getText()));
                Long billid = GeneralMedicineOutlet.addGeneralMedicineOutlet(gm);
                if (billid == -1) {
                    Alert a3 = new Alert(Alert.AlertType.INFORMATION);
                    a3.setTitle("Some");
                    a3.setContentText("Id not found");
                    a3.showAndWait();
                }
                System.out.println("Success");
                sum+=gm.getCost();
            }
        }

       hbox.setVisible(true);
       tv.setText(""+sum);
       submitBtn.setDisable(true);
   }



    @FXML
    void homeButton(ActionEvent event) throws IOException {
        patene.getScene().getWindow().hide();
        Stage dashbordClerk=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/dashbordClerk.fxml"));
        Scene scene=new Scene(root);
        dashbordClerk.setScene(scene);
        dashbordClerk.show();

    }


}


