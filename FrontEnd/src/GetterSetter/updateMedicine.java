package GetterSetter;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class updateMedicine {
    CheckBox select;
    String medName;
    TextField quantity;

    public updateMedicine(CheckBox select, String medName, TextField quantity) {
        this.select = select;
        this.medName = medName;
        this.quantity = quantity;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }
}
