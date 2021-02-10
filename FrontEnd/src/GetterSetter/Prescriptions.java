package GetterSetter;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Prescriptions {
    CheckBox select;
    String prescMedName;
    Integer prescStock;
    TextField presQuantity;
    Double prescPrice;
    CheckBox morning,afternoon,evening;

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public String getPrescMedName() {
        return prescMedName;
    }

    public void setPrescMedName(String prescMedName) {
        this.prescMedName = prescMedName;
    }

    public Integer getPrescStock() {
        return prescStock;
    }

    public void setPrescStock(Integer prescStock) {
        this.prescStock = prescStock;
    }

    public TextField getPresQuantity() {
        return presQuantity;
    }

    public void setPresQuantity(TextField presQuantity) {
        this.presQuantity = presQuantity;
    }

    public Double getPrescPrice() {
        return prescPrice;
    }

    public void setPrescPrice(Double prescPrice) {
        this.prescPrice = prescPrice;
    }

    public CheckBox getMorning() {
        return morning;
    }

    public void setMorning(CheckBox morning) {
        this.morning = morning;
    }

    public CheckBox getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(CheckBox afternoon) {
        this.afternoon = afternoon;
    }

    public CheckBox getEvening() {
        return evening;
    }

    public void setEvening(CheckBox evening) {
        this.evening = evening;
    }
}
