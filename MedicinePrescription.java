import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class MedicinePrescription {
    private long id = 0L;
    private long complaintId = 0L;
    private long medicineId = 0L;
    private int quantity = 0;
    private boolean morning = false, afternoon = false, night = false;
    private double cost = 0.0;

    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public MedicinePrescription() {}

    public MedicinePrescription(long complaintId, long medicineId, int quantity, boolean morning, boolean afternoon, boolean night) {
        this.complaintId = complaintId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.morning = morning;
        this.afternoon = afternoon;
        this.night = night;
    }

    public String toString() {
        String message = "";
        message += "Medicine Prescription ID: " + id + "\n";
        message += "Complaint ID: " + complaintId + "\n";
        message += "Medicine ID: " + medicineId + "\n";
        message += "Quantity: " + quantity + "\n";
        message += "Morning: " + morning + "\n";
        message += "Afternoon: " + afternoon + "\n";
        message += "Night: " + night + "\n";
        message += "Cost: " + cost;
        return message;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }

    public void setComplaintId(long complaintId) { this.complaintId = complaintId; }
    public long getComplaintId() { return complaintId; }

    public void setMedicineId(long medicineId) { this.medicineId = medicineId; }
    public long getMedicineId() { return medicineId; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return quantity; }

    public void setMorning(boolean morning) { this.morning = morning; }
    public boolean getMorning() { return morning; }

    public void setAfternoon(boolean afternoon) { this.afternoon = afternoon; }
    public boolean getAfternoon() { return afternoon; }

    public void setNight(boolean night) { this.night = night; }
    public boolean getNight() { return night; }

    public void setCost(double cost) { this.cost = cost; }
    public double getCost() { return cost; }

    public static long addMedicinePrescription(MedicinePrescription medicinePrescription) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            double cost = 0.0;
            preparedStatement = connection.prepareStatement("SELECT price,tax FROM Medicine WHERE id = ?;");
            preparedStatement.setLong(1, medicinePrescription.getMedicineId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double price = resultSet.getDouble(1);
                double tax = resultSet.getDouble(2);
                cost = price * (1 + (tax / 100)) * medicinePrescription.getQuantity();
                medicinePrescription.setCost(cost);
            } else {
                System.out.println("Medicine Id is not present.");
                return -1;
            }
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("INSERT INTO Medicine_Prescription VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setLong(1, medicinePrescription.getComplaintId());
            preparedStatement.setLong(2, medicinePrescription.getMedicineId());
            preparedStatement.setInt(3, medicinePrescription.getQuantity());
            preparedStatement.setBoolean(4, medicinePrescription.getMorning());
            preparedStatement.setBoolean(5, medicinePrescription.getAfternoon());
            preparedStatement.setBoolean(6, medicinePrescription.getNight());
            preparedStatement.setDouble(7, medicinePrescription.getCost());
            preparedStatement.setString(8,dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM Medicine_Prescription;");
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                medicinePrescription.setId(id);
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

    public static boolean removeMedicinePrescription(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("DELETE FROM Medicine_Prescription WHERE id = ?;");
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

    public static boolean updateMedicinePrescription(MedicinePrescription medicinePrescription) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            double cost = 0.0;
            preparedStatement = connection.prepareStatement("SELECT price,tax FROM Medicine WHERE id = ?;");
            preparedStatement.setLong(1, medicinePrescription.getMedicineId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double price = resultSet.getDouble(1);
                double tax = resultSet.getDouble(2);
                cost = price * (1 + (tax / 100)) * medicinePrescription.getQuantity();
                medicinePrescription.setCost(cost);
            } else {
                System.out.println("Medicine Id is not present.");
                return false;
            }
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("UPDATE Medicine_Prescription SET complaint_id = ?, medicine_id = ?, quantity = ?, morning = ?, afternoon = ?, night = ?, cost = ? WHERE id = ?;");
            preparedStatement.setLong(1, medicinePrescription.getComplaintId());
            preparedStatement.setLong(2, medicinePrescription.getMedicineId());
            preparedStatement.setInt(3, medicinePrescription.getQuantity());
            preparedStatement.setBoolean(4, medicinePrescription.getMorning());
            preparedStatement.setBoolean(5, medicinePrescription.getAfternoon());
            preparedStatement.setBoolean(6, medicinePrescription.getNight());
            preparedStatement.setDouble(7, medicinePrescription.getCost());
            preparedStatement.setLong(8, medicinePrescription.getId());
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

    public static boolean isMedicinePrescriptionPresent(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Medicine_Prescription WHERE id = ?;");
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

    public static MedicinePrescription getMedicinePrescription(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        MedicinePrescription medicinePrescription = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Medicine_Prescription WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                medicinePrescription = new MedicinePrescription();
                medicinePrescription.setId(resultSet.getLong(1));
                medicinePrescription.setComplaintId(resultSet.getLong(2));
                medicinePrescription.setMedicineId(resultSet.getLong(3));
                medicinePrescription.setQuantity(resultSet.getInt(4));
                medicinePrescription.setMorning(resultSet.getBoolean(5));
                medicinePrescription.setAfternoon(resultSet.getBoolean(6));
                medicinePrescription.setNight(resultSet.getBoolean(7));
                medicinePrescription.setCost(resultSet.getDouble(8));
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
        return medicinePrescription;
    }

    public static MedicinePrescription[] getMedicinePrescriptions(long complaintId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        MedicinePrescription[] medicinePrescriptions = new MedicinePrescription[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Medicine_Prescription WHERE complaint_id = ?;");
            preparedStatement.setLong(1, complaintId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<MedicinePrescription> resultMedicinePrescription = new ArrayList<>();
            while (resultSet.next()) {
                MedicinePrescription medicinePrescription = new MedicinePrescription();
                medicinePrescription.setId(resultSet.getLong(1));
                medicinePrescription.setComplaintId(resultSet.getLong(2));
                medicinePrescription.setMedicineId(resultSet.getLong(3));
                medicinePrescription.setQuantity(resultSet.getInt(4));
                medicinePrescription.setMorning(resultSet.getBoolean(5));
                medicinePrescription.setAfternoon(resultSet.getBoolean(6));
                medicinePrescription.setNight(resultSet.getBoolean(7));
                medicinePrescription.setCost(resultSet.getDouble(8));
                resultMedicinePrescription.add(medicinePrescription);
            }
            if (resultMedicinePrescription.size() > 0) {
                medicinePrescriptions = resultMedicinePrescription.toArray(medicinePrescriptions);
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
        return medicinePrescriptions;
    }
}