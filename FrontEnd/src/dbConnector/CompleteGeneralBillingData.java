package dbConnector;

import java.util.ArrayList;

public class CompleteGeneralBillingData {
    private GeneralBilling generalBilling = null;
    private GeneralMedicineData[] generalMedicineDatas = null;

    public GeneralBilling getGeneralBilling() { return generalBilling; }
    public GeneralMedicineData[] getGeneralMedicineDatas() { return generalMedicineDatas; }

    public CompleteGeneralBillingData(long billId) throws Exception {
        if (GeneralBilling.isGeneralBillingPresent(billId)) {
            generalBilling = GeneralBilling.getGeneralBilling(billId);
            GeneralMedicineData[] generalMedicineDatas = new GeneralMedicineData[0];
            ArrayList<GeneralMedicineData> datas = new ArrayList<>();
            for (GeneralMedicineOutlet generalMedicineOutlet : GeneralMedicineOutlet.getGeneralMedicineOutlets(billId)) {
                datas.add(new GeneralMedicineData(Medicine.getMedicine(generalMedicineOutlet.getMedicineId()), generalMedicineOutlet));
            }
            generalMedicineDatas = datas.toArray(generalMedicineDatas);
            this.generalMedicineDatas = generalMedicineDatas;
        } else {
            throw new Exception("Bill Id not present.");
        }
    }

    class GeneralMedicineData {
        Medicine medicine = null;
        GeneralMedicineOutlet generalMedicineOutlet = null;

        public GeneralMedicineData(Medicine medicine, GeneralMedicineOutlet generalMedicineOutlet) {
            this.medicine = medicine;
            this.generalMedicineOutlet = generalMedicineOutlet;
        }

        public Medicine getMedicine() { return medicine; }
        public GeneralMedicineOutlet getGeneralMedicineOutlet() { return generalMedicineOutlet; }
    }

    public static void main(String[] args) throws Exception {
        CompleteGeneralBillingData c = new CompleteGeneralBillingData(1L);
        System.out.println(c.getGeneralBilling());
        for (GeneralMedicineData generalMedicineData: c.getGeneralMedicineDatas()) {
            System.out.println(generalMedicineData.getMedicine());
            System.out.println(generalMedicineData.getGeneralMedicineOutlet());
        }
    }
}