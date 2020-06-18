import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Billing {
    private long id = 0L;
    private long complaintId = 0L;
    private int consultationFee = 0;
    private double totalFee = 0.0;

    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Billing() {}

    public Billing(long complaintId, int consultationFee) {
        this.complaintId = complaintId;
        this.consultationFee = consultationFee;
    }

    public String toString() {
        String message = "";
        message += "Billing ID: " + id + "\n";
        message += "Complaint ID: " + complaintId + "\n";
        message += "Consultation Fee: " + consultationFee + "\n";
        message += "Total Fee: " + totalFee;
        return message;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }

    public void setComplaintId(long complaintId) { this.complaintId = complaintId; }
    public long getComplaintId() { return complaintId; }

    public void setConsultationFee(int consultationFee) { this.consultationFee = consultationFee; }
    public int getConsultationFee() { return consultationFee; }

    public void setTotalFee(double totalFee) { this.totalFee = totalFee; }
    public double getTotalFee() { return totalFee; }

    public static long addBilling(Billing billing) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            double totalFee = 0.0;
            preparedStatement = connection.prepareStatement("SELECT cost FROM Medicine_Prescription WHERE complaint_id = ?;");
            preparedStatement.setLong(1, billing.getComplaintId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalFee += resultSet.getDouble(1);
            }
            totalFee += billing.getConsultationFee();
            billing.setTotalFee(totalFee);
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("INSERT INTO Billing VALUES (0, ?, ?, ?, ?);");
            preparedStatement.setLong(1, billing.getComplaintId());
            preparedStatement.setInt(2, billing.getConsultationFee());
            preparedStatement.setDouble(3, billing.getTotalFee());
            preparedStatement.setString(4, dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM Billing;");
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                billing.setId(id);
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

    public static boolean removeBilling(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("DELETE FROM Billing WHERE id = ?;");
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

    public static boolean updateBilling(Billing billing) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            double totalFee = 0.0;
            preparedStatement = connection.prepareStatement("SELECT cost FROM Medicine_Prescription WHERE complaint_id = ?;");
            preparedStatement.setLong(1, billing.getComplaintId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalFee += resultSet.getDouble(1);
            }
            totalFee += billing.getConsultationFee();
            billing.setTotalFee(totalFee);
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("UPDATE Billing SET complaint_id = ?, consultation_fee = ?, total_fee = ? WHERE id = ?;");
            preparedStatement.setLong(1, billing.getComplaintId());
            preparedStatement.setInt(2, billing.getConsultationFee());
            preparedStatement.setDouble(3, billing.getTotalFee());
            preparedStatement.setLong(4, billing.getId());
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

    public static boolean isBillingPresent(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Billing WHERE id = ?;");
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

    public static Billing getBilling(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Billing billing = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Billing WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                billing = new Billing();
                billing.setId(resultSet.getLong(1));
                billing.setComplaintId(resultSet.getLong(2));
                billing.setConsultationFee(resultSet.getInt(3));
                billing.setTotalFee(resultSet.getDouble(4));
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
        return billing;
    }

    public static Billing getBillingWithComplaintId(long complaintId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Billing billing = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Billing WHERE complaint_id = ?;");
            preparedStatement.setLong(1, complaintId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                billing = new Billing();
                billing.setId(resultSet.getLong(1));
                billing.setComplaintId(resultSet.getLong(2));
                billing.setConsultationFee(resultSet.getInt(3));
                billing.setTotalFee(resultSet.getDouble(4));
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
        return billing;
    }

    public static Billing[] getBillings(long patientId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Billing[] billings = new Billing[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT b.id,b.complaint_id,b.consultation_fee,b.total_fee FROM Billing b, Complaint c WHERE b.complaint_id = c.id AND c.patient_id = ?;");
            preparedStatement.setLong(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Billing> resultBilling = new ArrayList<>();
            while (resultSet.next()) {
                Billing billing = new Billing();
                billing.setId(resultSet.getLong(1));
                billing.setComplaintId(resultSet.getLong(2));
                billing.setConsultationFee(resultSet.getInt(3));
                billing.setTotalFee(resultSet.getDouble(4));
                resultBilling.add(billing);
            }
            if (resultBilling.size() > 0) {
                billings = resultBilling.toArray(billings);
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
        return billings;
    }
}