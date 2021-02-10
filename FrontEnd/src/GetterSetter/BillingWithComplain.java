package GetterSetter;

public class BillingWithComplain {
    Long id;
    String MedName;
    Integer quantity;
    Double price;
    Boolean morning;
    Boolean Afternoon;
    Boolean night;


    public BillingWithComplain(Long id, String medName, Integer quantity, Double price, Boolean morning, Boolean afternoon, Boolean night) {
        this.id = id;
        MedName = medName;
        this.quantity = quantity;
        this.price = price;
        this.morning = morning;
        Afternoon = afternoon;
        this.night = night;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedName() {
        return MedName;
    }

    public void setMedName(String medName) {
        MedName = medName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getMorning() {
        return morning;
    }

    public void setMorning(Boolean morning) {
        this.morning = morning;
    }

    public Boolean getAfternoon() {
        return Afternoon;
    }

    public void setAfternoon(Boolean afternoon) {
        Afternoon = afternoon;
    }

    public Boolean getNight() {
        return night;
    }

    public void setNight(Boolean night) {
        this.night = night;
    }
}
