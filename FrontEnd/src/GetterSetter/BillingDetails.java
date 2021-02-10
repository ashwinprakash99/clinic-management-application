package GetterSetter;

public class BillingDetails {
    Long id,CompId;
    Double totfee;
    int consultation;

    public BillingDetails(Long id, Long compId, int consultation, Double totfee) {
        this.id = id;
        CompId = compId;
        this.consultation = consultation;
        this.totfee = totfee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompId() {
        return CompId;
    }

    public void setCompId(Long compId) {
        CompId = compId;
    }

    public int getConsultation() {
        return consultation;
    }

    public void setConsultation(int consultation) {
        this.consultation = consultation;
    }

    public Double getTotfee() {
        return totfee;
    }

    public void setTotfee(Double totfee) {
        this.totfee = totfee;
    }
}
