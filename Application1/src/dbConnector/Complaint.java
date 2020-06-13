package dbConnector;

import java.util.Date;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Complaint {
    private long id = 0L;
    private long patientId = 0L;
    private String complaint1 = null, complaint2 = "", complaint3 = "";
    private String explanation1 = null, explanation2 = "", explanation3 = "";
    private Date createdAt = new Date();

    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Complaint() {}

    public Complaint(long patientId, String complaint1, String complaint2, String complaint3, String explanation1, String explanation2, String explanation3) {
        this.patientId = patientId;
        this.complaint1 = complaint1;
        this.complaint2 = complaint2;
        this.complaint3 = complaint3;
        this.explanation1 = explanation1;
        this.explanation2 = explanation2;
        this.explanation3 = explanation3;
    }

    public String toString() {
        String message = "";
        message += "Complaint ID: " + id + "\n";
        message += "Patient ID: " + patientId + "\n";
        message += "Complaint 1: " + complaint1 + "\n";
        message += "Complaint 2: " + complaint2 + "\n";
        message += "Complaint 3: " + complaint3 + "\n";
        message += "Explanation 1: " + explanation1 + "\n";
        message += "Explanation 2: " + explanation2 + "\n";
        message += "Explanation 3: " + explanation3 + "\n";
        message += "Date: " + createdAt;
        return message;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return this.id; }

    public void setPatientId(long patientId) { this.patientId = patientId; }
    public long getPatientId() { return this.patientId; }

    public void setComplaint1(String complaint1) { this.complaint1 = complaint1; }
    public String getComplaint1() { return this.complaint1; }

    public void setComplaint2(String complaint2) { this.complaint2 = complaint2; }
    public String getComplaint2() { return this.complaint2; }

    public void setComplaint3(String complaint3) { this.complaint3 = complaint3; }
    public String getComplaint3() { return this.complaint3; }

    public void setExplanation1(String explanation1) { this.explanation1 = explanation1; }
    public String getExplanation1() { return this.explanation1; }

    public void setExplanation2(String explanation2) { this.explanation2 = explanation2; }
    public String getExplanation2() { return this.explanation2; }

    public void setExplanation3(String explanation3) { this.explanation3 = explanation3; }
    public String getExplanation3() { return this.explanation3; }

    public void setCreatedAt(String createdAt) throws ParseException{ this.createdAt = dff.parse(createdAt); }
    public String getCreatedAt() { return dff.format(this.createdAt); }

    public static long addComplaint(Complaint complaint) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("INSERT INTO Complaint VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setLong(1, complaint.getPatientId());
            preparedStatement.setString(2, complaint.getComplaint1());
            preparedStatement.setString(3, complaint.getComplaint2());
            preparedStatement.setString(4, complaint.getComplaint3());
            preparedStatement.setString(5, complaint.getExplanation1());
            preparedStatement.setString(6, complaint.getExplanation2());
            preparedStatement.setString(7, complaint.getExplanation3());
            preparedStatement.setString(8, dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM Complaint;");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                complaint.setId(id);
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

    public static boolean removeComplaint(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("DELETE FROM Complaint WHERE id = ?;");
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

    public static boolean updateComplaint(Complaint complaint) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("UPDATE Complaint SET patient_id = ?, complaint1 = ?, complaint2 = ?, complaint3 = ?, explanation1 = ?, explanation2 = ?, explanation3 = ? WHERE id = ?;");
            preparedStatement.setLong(1, complaint.getPatientId());
            preparedStatement.setString(2, complaint.getComplaint1());
            preparedStatement.setString(3, complaint.getComplaint2());
            preparedStatement.setString(4, complaint.getComplaint3());
            preparedStatement.setString(5, complaint.getExplanation1());
            preparedStatement.setString(6, complaint.getExplanation2());
            preparedStatement.setString(7, complaint.getExplanation3());
            preparedStatement.setLong(8, complaint.getId());
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

    public static boolean isComplaintPresent(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Complaint WHERE id = ?;");
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

    public static Complaint getComplaint(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Complaint complaint = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Complaint WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                complaint = new Complaint();
                complaint.setId(resultSet.getLong(1));
                complaint.setPatientId(resultSet.getLong(2));
                complaint.setComplaint1(resultSet.getString(3));
                complaint.setComplaint2(resultSet.getString(4));
                complaint.setComplaint3(resultSet.getString(5));
                complaint.setExplanation1(resultSet.getString(6));
                complaint.setExplanation2(resultSet.getString(7));
                complaint.setExplanation3(resultSet.getString(8));
                complaint.setCreatedAt(resultSet.getString(9));
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
        return complaint;
    }

    public static Complaint[] getComplaints(long patientId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Complaint[] complaints = new Complaint[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Complaint WHERE patient_id = ?;");
            preparedStatement.setLong(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Complaint> resultComplaint = new ArrayList<>();
            while (resultSet.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(resultSet.getLong(1));
                complaint.setPatientId(resultSet.getLong(2));
                complaint.setComplaint1(resultSet.getString(3));
                complaint.setComplaint2(resultSet.getString(4));
                complaint.setComplaint3(resultSet.getString(5));
                complaint.setExplanation1(resultSet.getString(6));
                complaint.setExplanation2(resultSet.getString(7));
                complaint.setExplanation3(resultSet.getString(8));
                complaint.setCreatedAt(resultSet.getString(9));
                resultComplaint.add(complaint);
            }
            if (resultComplaint.size() > 0) {
                complaints = resultComplaint.toArray(complaints);
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
        return complaints;
    }
}