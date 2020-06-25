package GetterSetter;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Generalmedicine {
    CheckBox select;
    String medName;
    Integer stock;
    TextField quantity;
    double cost;

    public Generalmedicine(CheckBox select, String medName, Integer stock, TextField quantity, double cost) {
        this.select = select;
        this.medName = medName;
        this.stock = stock;
        this.quantity = quantity;
        this.cost = cost;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

