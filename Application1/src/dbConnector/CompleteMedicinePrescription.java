package dbConnector;
import java.util.*;

public class CompleteMedicinePrescription {
    private Medicine medicine = null;
    private MedicinePrescription medicinePrescription = null;

    public Medicine getMedicine() { return medicine; }
    public MedicinePrescription getMedicinePrescription() { return medicinePrescription; }

    public CompleteMedicinePrescription(Medicine medicine, MedicinePrescription medicinePrescription) {
        this.medicine = medicine;
        this.medicinePrescription = medicinePrescription;
    }

    public static CompleteMedicinePrescription[] getCompleteMedicineData(long complaintId) throws Exception {
        CompleteMedicinePrescription[] completeMedicinePrescriptions = new CompleteMedicinePrescription[0];
        ArrayList<CompleteMedicinePrescription> datas = new ArrayList<>();
        if (Complaint.isComplaintPresent(complaintId)) {
            for (MedicinePrescription medicinePrescription : MedicinePrescription.getMedicinePrescriptions(complaintId)) {
                Medicine medicine = Medicine.getMedicine(medicinePrescription.getMedicineId());
                CompleteMedicinePrescription completeMedicinePrescription = new CompleteMedicinePrescription(medicine, medicinePrescription);
                datas.add(completeMedicinePrescription);
            }
            completeMedicinePrescriptions = datas.toArray(completeMedicinePrescriptions);
        } else {
            throw new Exception("Complaint Id not present.");
        }
        return completeMedicinePrescriptions;
    }
}

