package controller;

import dbConnector.CompleteMedicinePrescription;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class PrintMedicinePage implements Initializable {
    @FXML
    private TextArea printMedicine;

    Long complainId=BillingWithComplain.compId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            CompleteMedicinePrescription[] cp=CompleteMedicinePrescription.getCompleteMedicineData(complainId);
            for(int i=0;i<cp.length;i++){

                printMedicine.appendText("\n\t");
                printMedicine.appendText(""+(i+1));
                printMedicine.appendText("      |      ");
                printMedicine.appendText(cp[i].getMedicine().getMedicineName());
                printMedicine.appendText("      |      ");
                printMedicine.appendText(""+cp[i].getMedicinePrescription().getMorning());
                printMedicine.appendText("      |      ");
                printMedicine.appendText(""+cp[i].getMedicinePrescription().getAfternoon());
                printMedicine.appendText("      |      ");
                printMedicine.appendText(""+cp[i].getMedicinePrescription().getNight());
                printMedicine.appendText("      |      ");
                printMedicine.appendText(""+cp[i].getMedicinePrescription().getQuantity());
                printMedicine.appendText("      |      ");
                printMedicine.appendText(""+cp[i].getMedicinePrescription().getCost());

            }





        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
