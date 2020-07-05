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
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

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

    @FXML
    private Button okBt;

    @FXML
    private Button updateBtn;

    static Long compId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        feesBox.setVisible(false);
    }


    @FXML
    void checkComplaint(ActionEvent event)  {
        try {
            CompleteMedicinePrescription[] cp=CompleteMedicinePrescription.getCompleteMedicineData(Long.parseLong(complaintId.getText()));
            compId=Long.parseLong(complaintId.getText());
            ObservableList<GetterSetter.BillingWithComplain> list = FXCollections.observableArrayList();


                tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
                tabMed.setCellValueFactory(new PropertyValueFactory<>("MedName"));
                tabQntty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                tabPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                morn.setCellValueFactory(new PropertyValueFactory<>("morning"));
                after.setCellValueFactory(new PropertyValueFactory<>("Afternoon"));
                nig.setCellValueFactory(new PropertyValueFactory<>("night"));

                for (int i = 0; i < cp.length; i++) {
                    list.add(new GetterSetter.BillingWithComplain((long) (i + 1), cp[i].getMedicine().getMedicineName(), cp[i].getMedicinePrescription().getQuantity(), cp[i].getMedicinePrescription().getCost(), cp[i].getMedicinePrescription().getMorning(), cp[i].getMedicinePrescription().getAfternoon(), cp[i].getMedicinePrescription().getNight()));
                    tableView.setItems(list);
                }
            }


        catch (Exception e){
            Alert ab = new Alert(Alert.AlertType.INFORMATION);
           boolean b1=Pattern.matches("\\d*",complaintId.getText());
           if(b1==false)
           {

               ab.setTitle("Wrong Information");
               ab.setContentText("Please enter numeric value");
               ab.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

           }
           else
             ab.setContentText("Complaint ID not found in database");
           ab.showAndWait();

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
            okBt.setDisable(true);
            updateBtn.setDisable(true);
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
                else
                {

                    MedicinePrescription mp=new MedicinePrescription();
                    mp.setId(cp[i].getMedicinePrescription().getId());
                    mp.setComplaintId(Long.parseLong(complaintId.getText()));
                    mp.setMedicineId(cp[i].getMedicinePrescription().getMedicineId());
                    mp.setQuantity(0);
                    mp.setMorning(cp[i].getMedicinePrescription().getMorning());
                    mp.setAfternoon(cp[i].getMedicinePrescription().getAfternoon());
                    mp.setNight(cp[i].getMedicinePrescription().getNight());

                    boolean v=MedicinePrescription.updateMedicinePrescription(mp);

                }
            }
                }


        }

    public void printClick(ActionEvent actionEvent) throws Exception {
//        Stage dashbordClerk=new Stage();
//        Parent root= FXMLLoader.load(getClass().getResource("/fxml/printMedicinePage.fxml"));
//        Scene scene=new Scene(root);
//        dashbordClerk.setScene(scene);
//        dashbordClerk.show();

//        String outputFile="C:\\otherFile\\ClilnicManagmentProject\\New folder\\sample.pdf";
//
//        List<GetterSetter.BillingWithComplain> list=new ArrayList<GetterSetter.BillingWithComplain>();
//
//        //ObservableList<GetterSetter.BillingWithComplain> list= FXCollections.observableArrayList();
//
//
//        CompleteMedicinePrescription[] cp=CompleteMedicinePrescription.getCompleteMedicineData(Long.parseLong(complaintId.getText()));
//
//        for(int i=0;i<cp.length;i++){
//            list.add(new GetterSetter.BillingWithComplain((long) (i + 1), cp[i].getMedicine().getMedicineName(), cp[i].getMedicinePrescription().getQuantity(), cp[i].getMedicinePrescription().getCost(), cp[i].getMedicinePrescription().getMorning(), cp[i].getMedicinePrescription().getAfternoon(), cp[i].getMedicinePrescription().getNight()));
//            System.out.println((long) (i + 1)+ cp[i].getMedicine().getMedicineName()+ cp[i].getMedicinePrescription().getQuantity()+ cp[i].getMedicinePrescription().getCost()+ cp[i].getMedicinePrescription().getMorning()+ cp[i].getMedicinePrescription().getAfternoon()+ cp[i].getMedicinePrescription().getNight());
//
//        }
//
//
//        JRBeanCollectionDataSource itemJRBean=new JRBeanCollectionDataSource(list);
//
//        Map<String,Object> parameter= new HashMap<>();
//        parameter.put("CollectionBeanParameter",itemJRBean);
//
//        InputStream input=new FileInputStream(new File("C:\\otherFile\\ClilnicManagmentProject\\frontend\\Application1\\src\\image\\med.jrxml"));
//
//        JasperDesign jasperDesign= JRXmlLoader.load(input);
//
//        JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
//
//        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameter,new JREmptyDataSource());
//
//        JasperViewer.viewReport(jasperPrint);

        //output
//        OutputStream outputStream=new FileOutputStream(new File(outputFile));
//        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);


//        System.out.println("Success");


        /* Convert List to JRBeanCollectionDataSource */
//        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);
//
//        /* Map to hold Jasper report Parameters */
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("CollectionBeanParam", itemsJRBean);
//
//        //read jrxml file and creating jasperdesign object
//        InputStream input = new FileInputStream(new File("C:\\otherFile\\ClilnicManagmentProject\\New folder\\medicine.jrxml"));
//
//        JasperDesign jasperDesign = JRXmlLoader.load(input);
//
//        /*compiling jrxml with help of JasperReport class*/
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//        /* Using jasperReport object to generate PDF */
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//
//        /*call jasper engine to display report in jasperviewer window*/
//        JasperViewer.viewReport(jasperPrint);
//
//
//        /* outputStream to create PDF */
//        //OutputStream outputStream = new FileOutputStream(new File(outputFile));
//
//
//        /* Write content to PDF file */
//        //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//        System.out.println("File Generated");




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
