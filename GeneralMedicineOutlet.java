import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class GeneralMedicineOutlet {
    private long id = 0L;
    private long billId = 0L;
    private long medicineId = 0L;
    private int quantity = 0;
    private double cost = 0.0;

    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public GeneralMedicineOutlet() {}

    public GeneralMedicineOutlet(long billId, long medicineId, int quantity) {
        this.billId = billId;
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public String toString() {
        String message = "";
        message += "General Medicine Outlet ID: " + id + "\n";
        message += "General Medicine Bill ID: " + billId + "\n";
        message += "Medicine ID: " + medicineId + "\n";
        message += "Quantity: " + quantity + "\n";
        message += "Cost: " + cost;
        return message;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }

    public void setBillId(long billId) { this.billId = billId; }
    public long getBillId() { return billId; }

    public void setMedicineId(long medicineId) { this.medicineId = medicineId; }
    public long getMedicineId() { return medicineId; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return quantity; }

    public void setCost(double cost) { this.cost = cost; }
    public double getCost() { return cost; }

    public static long addGeneralMedicineOutlet(GeneralMedicineOutlet generalMedicineOutlet) {
        PreparedStatement preparedStatement = null;
        try {
            double cost = 0.0;
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT price FROM Medicine WHERE id = ?;");
            preparedStatement.setLong(1, generalMedicineOutlet.getMedicineId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double price = resultSet.getDouble(1);
                cost = price  * generalMedicineOutlet.getQuantity();
                generalMedicineOutlet.setCost((Math.round(cost*10.0)/10.0));
            } else {
                System.out.println("Medicine Id is not present.");
                return -1;
            }
            preparedStatement.close();
            preparedStatement = MainDataConnection.connection.prepareStatement("INSERT INTO General_Medicine_Outlet VALUES (0, ?, ?, ?, ?, ?);");
            preparedStatement.setLong(1, generalMedicineOutlet.getBillId());
            preparedStatement.setLong(2, generalMedicineOutlet.getMedicineId());
            preparedStatement.setInt(3, generalMedicineOutlet.getQuantity());
            preparedStatement.setDouble(4, generalMedicineOutlet.getCost());
            preparedStatement.setString(5, dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = MainDataConnection.connection.prepareStatement("SELECT MAX(id) FROM General_Medicine_Outlet;");
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                generalMedicineOutlet.setId(id);
                return id;
            } else {
                System.out.println("No records added");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static boolean removeGeneralMedicineOutlet(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("DELETE FROM General_Medicine_Outlet WHERE id = ?;");
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean updateGeneralMedicineOutlet(GeneralMedicineOutlet generalMedicineOutlet) {
        PreparedStatement preparedStatement = null;
        try {
            double cost = 0.0;
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT price FROM Medicine WHERE id = ?;");
            preparedStatement.setLong(1, generalMedicineOutlet.getMedicineId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double price = resultSet.getDouble(1);
                cost = price  * generalMedicineOutlet.getQuantity();
                generalMedicineOutlet.setCost((Math.round(cost*10.0)/10.0));
            } else {
                System.out.println("Medicine Id is not present.");
                return false;
            }
            preparedStatement.close();
            preparedStatement = MainDataConnection.connection.prepareStatement("UPDATE General_Medicine_Outlet SET bill_id = ?, medicine_id = ?, quantity = ?, cost = ? WHERE id = ?;");
            preparedStatement.setLong(1, generalMedicineOutlet.getBillId());
            preparedStatement.setLong(2, generalMedicineOutlet.getMedicineId());
            preparedStatement.setInt(3, generalMedicineOutlet.getQuantity());
            preparedStatement.setDouble(4, generalMedicineOutlet.getCost());
            preparedStatement.setLong(5, generalMedicineOutlet.getId());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isGeneralMedicineOutletPresent(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT * FROM General_Medicine_Outlet WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                ++count;
            }
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static GeneralMedicineOutlet getGeneralMedicineOutlet(long id) {
        PreparedStatement preparedStatement = null;
        GeneralMedicineOutlet generalMedicineOutlet = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT * FROM General_Medicine_Outlet WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                generalMedicineOutlet = new GeneralMedicineOutlet();
                generalMedicineOutlet.setId(resultSet.getLong(1));
                generalMedicineOutlet.setBillId(resultSet.getLong(2));
                generalMedicineOutlet.setMedicineId(resultSet.getLong(3));
                generalMedicineOutlet.setQuantity(resultSet.getInt(4));
                generalMedicineOutlet.setCost(resultSet.getDouble(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return generalMedicineOutlet;
    }

    public static GeneralMedicineOutlet[] getGeneralMedicineOutlets(long billId) {
        PreparedStatement preparedStatement = null;
        GeneralMedicineOutlet[] generalMedicineOutlets = new GeneralMedicineOutlet[0];
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT * FROM General_Medicine_Outlet WHERE bill_id = ?;");
            preparedStatement.setLong(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<GeneralMedicineOutlet> resultGeneralMedicineOutlet = new ArrayList<>();
            while (resultSet.next()) {
                GeneralMedicineOutlet generalMedicineOutlet = new GeneralMedicineOutlet();
                generalMedicineOutlet.setId(resultSet.getLong(1));
                generalMedicineOutlet.setBillId(resultSet.getLong(2));
                generalMedicineOutlet.setMedicineId(resultSet.getLong(3));
                generalMedicineOutlet.setQuantity(resultSet.getInt(4));
                generalMedicineOutlet.setCost(resultSet.getDouble(5));
                resultGeneralMedicineOutlet.add(generalMedicineOutlet);
            }
            if (resultGeneralMedicineOutlet.size() > 0) {
                generalMedicineOutlets = resultGeneralMedicineOutlet.toArray(generalMedicineOutlets);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return generalMedicineOutlets;
    }
}