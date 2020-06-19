package dbConnector;

import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Medicine {
    private long id = 0L;
    private String medicineName = null;
    private double price = 0.0;
    private double tax = 0.0;
    private int quantity = 0;

    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Medicine() {}

    public Medicine(String medicineName, double price, double tax, int quantity) {
        this.medicineName = medicineName;
        this.price = price;
        this.tax = tax;
        this.quantity = quantity;
    }

    public String toString() {
        String message = "";
        message += "Medicine ID: " + id + "\n";
        message += "Medicine Name: " + medicineName + "\n";
        message += "Price: " + price + "\n";
        message += "Tax: " + tax + "\n";
        message += "Quantity: " + quantity;
        return message;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }

    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    public String getMedicineName() { return medicineName; }

    public void setPrice(double price) { this.price = price; }
    public double getPrice() {return price; }

    public void setTax(double tax) { this.tax = tax; }
    public double getTax() { return tax; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return quantity; }

    public static long addMedicine(Medicine medicine) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("INSERT INTO Medicine VALUES (0, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, medicine.getMedicineName());
            preparedStatement.setDouble(2, medicine.getPrice());
            preparedStatement.setDouble(3, medicine.getTax());
            preparedStatement.setInt(4, medicine.getQuantity());
            preparedStatement.setString(5, dff.format(new Date()));
            preparedStatement.setString(6, dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM Medicine;");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                medicine.setId(id);
                return id;
            } else {
                System.out.println("No records added");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static boolean removeMedicine(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("DELETE FROM Medicine WHERE id = ?;");
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
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean updateMedicine(Medicine medicine) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("UPDATE Medicine SET medicine_name = ?, price = ?, tax = ?, quantity = ?, modified_at = ? WHERE id = ?;");
            preparedStatement.setString(1, medicine.getMedicineName());
            preparedStatement.setDouble(2, medicine.getPrice());
            preparedStatement.setDouble(3, medicine.getTax());
            preparedStatement.setInt(4, medicine.getQuantity());
            preparedStatement.setString(5, dff.format(new Date()));
            preparedStatement.setLong(6, medicine.getId());
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
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isMedicinePresent(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Medicine WHERE id = ?;");
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
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Medicine getMedicine(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medicine medicine = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Medicine WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                medicine = new Medicine();
                medicine.setId(resultSet.getLong(1));
                medicine.setMedicineName(resultSet.getString(2));
                medicine.setPrice(resultSet.getDouble(3));
                medicine.setTax(resultSet.getDouble(4));
                medicine.setQuantity(resultSet.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return medicine;
    }

    public static Medicine[] getMedicines(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medicine[] medicines = new Medicine[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Medicine WHERE UPPER(medicine_name) LIKE ?;");
            preparedStatement.setString(1, "%"+name.toUpperCase()+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Medicine> resultMedicine = new ArrayList<>();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getLong(1));
                medicine.setMedicineName(resultSet.getString(2));
                medicine.setPrice(resultSet.getDouble(3));
                medicine.setTax(resultSet.getDouble(4));
                medicine.setQuantity(resultSet.getInt(5));
                resultMedicine.add(medicine);
            }
            if (resultMedicine.size() > 0) {
                medicines = resultMedicine.toArray(medicines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return medicines;
    }

    public static Medicine[] getAllMedicines() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medicine[] medicines = new Medicine[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Medicine;");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Medicine> resultMedicine = new ArrayList<>();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getLong(1));
                medicine.setMedicineName(resultSet.getString(2));
                medicine.setPrice(resultSet.getDouble(3));
                medicine.setTax(resultSet.getDouble(4));
                medicine.setQuantity(resultSet.getInt(5));
                resultMedicine.add(medicine);
            }
            if (resultMedicine.size() > 0) {
                medicines = resultMedicine.toArray(medicines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return medicines;
    }

    public static Medicine[] getAllLowQuantityMedicines(int minQuantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medicine[] medicines = new Medicine[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Medicine WHERE quantity < ?;");
            preparedStatement.setInt(1, minQuantity);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Medicine> resultMedicine = new ArrayList<>();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getLong(1));
                medicine.setMedicineName(resultSet.getString(2));
                medicine.setPrice(resultSet.getDouble(3));
                medicine.setTax(resultSet.getDouble(4));
                medicine.setQuantity(resultSet.getInt(5));
                resultMedicine.add(medicine);
            }
            if (resultMedicine.size() > 0) {
                medicines = resultMedicine.toArray(medicines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return medicines;
    }
}