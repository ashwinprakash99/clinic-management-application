package dbConnector;

import java.util.*;

public class CompleteDataForComplaint {
    private Billing billing = null;
    private Complaint complaint = null;
    private Examination examination = null;
    private MedicinePrescription[] medicinePrescriptions = null;
    private Patient patient = null;
    private Treatment treatment = null;

    public Billing getBilling() { return billing; }
    public Complaint getComplaint() { return complaint; }
    public Examination getExamination() { return examination; }
    public MedicinePrescription[] getMedicinePrescriptions() { return medicinePrescriptions; }
    public Patient getPatient() { return patient; }
    public Treatment getTreatment() { return treatment; }

    public CompleteDataForComplaint(long complaintId) throws Exception {
        if (Complaint.isComplaintPresent(complaintId)) {
            complaint = Complaint.getComplaint(complaintId);
        } else {
            throw new Exception("Complaint Id not present.");
        }
        if (Patient.isPatientPresent(complaint.getPatientId())) {
            patient = Patient.getPatient(complaint.getPatientId());
            
        } else {
            throw new Exception("Patient Id not present.");
        }
        Billing billing = Billing.getBillingWithComplaintId(complaintId);
        if (billing == null) {
            throw new Exception("No Billing found.");
        } else {
            this.billing = billing;
        }
        Examination examination = Examination.getExaminationWithComplaintId(complaintId);
        if (examination == null) {
            throw new Exception("No Examination found.");
        } else {
            this.examination = examination;
        }
        medicinePrescriptions = MedicinePrescription.getMedicinePrescriptions(complaintId);
        Treatment treatment = Treatment.getTreatmentWithComplaintId(complaintId);
        if (treatment == null) {
            throw new Exception("No Treatment found.");
        } else {
            this.treatment = treatment;
        }

    }

    public static CompleteDataForComplaint[] getDataForPatientId(long patientId) throws Exception {
        CompleteDataForComplaint[] completeDataForComplaints = new CompleteDataForComplaint[0];
        Complaint[] complaints = Complaint.getComplaints(patientId);
        ArrayList<CompleteDataForComplaint> datas = new ArrayList<>();
        for (Complaint complaint : complaints) {
            CompleteDataForComplaint c = new CompleteDataForComplaint(complaint.getId());
            datas.add(c);
        }
        completeDataForComplaints = datas.toArray(completeDataForComplaints);
        return completeDataForComplaints;
    }
}