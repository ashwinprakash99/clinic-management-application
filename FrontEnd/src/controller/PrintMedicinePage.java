//package controller;
//
//import GetterSetter.BillingWithComplain;
//import dbConnector.CompleteMedicinePrescription;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TextArea;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;
//
//import java.io.*;
//import java.net.URL;
//import java.util.*;
//
//public class PrintMedicinePage  {
////    @FXML
////    private TextArea printMedicine;
////
//   Long compId= controller.BillingWithComplain.compId;
////
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////
////        try {
////            CompleteMedicinePrescription[] cp=CompleteMedicinePrescription.getCompleteMedicineData(complainId);
////            for(int i=0;i<cp.length;i++){
////
////                printMedicine.appendText("\n\t");
////                printMedicine.appendText(""+(i+1));
////                printMedicine.appendText("      |      ");
////                printMedicine.appendText(cp[i].getMedicine().getMedicineName());
////                printMedicine.appendText("      |      ");
////                printMedicine.appendText(""+cp[i].getMedicinePrescription().getMorning());
////                printMedicine.appendText("      |      ");
////                printMedicine.appendText(""+cp[i].getMedicinePrescription().getAfternoon());
////                printMedicine.appendText("      |      ");
////                printMedicine.appendText(""+cp[i].getMedicinePrescription().getNight());
////                printMedicine.appendText("      |      ");
////                printMedicine.appendText(""+cp[i].getMedicinePrescription().getQuantity());
////                printMedicine.appendText("      |      ");
////                printMedicine.appendText(""+cp[i].getMedicinePrescription().getCost());
////
////            }
////
////
////
////
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////
////    }
//
//
//    String outputFile="C:\\otherFile\\ClilnicManagmentProject\\New folder\\sample.pdf";
//
//    List<BillingWithComplain> list=new ArrayList<>();
//
//    CompleteMedicinePrescription[] cp=CompleteMedicinePrescription.getCompleteMedicineData(compId);
//
//
//
//
//    public PrintMedicinePage() throws Exception {
//        for(int i=0;i<cp.length;i++){
//            list.add(new GetterSetter.BillingWithComplain((long) (i + 1), cp[i].getMedicine().getMedicineName(), cp[i].getMedicinePrescription().getQuantity(), cp[i].getMedicinePrescription().getCost(), cp[i].getMedicinePrescription().getMorning(), cp[i].getMedicinePrescription().getAfternoon(), cp[i].getMedicinePrescription().getNight()));
//
//        }
//
//        JRBeanCollectionDataSource itemJRBean=new JRBeanCollectionDataSource(list);
//
//        Map<String,Object> parameter=new HashMap<String , Object>();
//        parameter.put("CollectionBeanParam",itemJRBean);
//
//        InputStream input=new FileInputStream(new File("C:\\otherFile\\ClilnicManagmentProject\\New folder\\sample_A4.jrxml"));
//
//        JasperDesign jasperDesign= JRXmlLoader.load(input);
//
//        JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
//
//        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameter,new JREmptyDataSource());
//
//        JasperViewer.viewReport(jasperPrint);
//
//        //output
////        OutputStream outputStream=new FileOutputStream(new File(outputFile));
////        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
//
//
//        System.out.println("Success");
//
//    }
//}
