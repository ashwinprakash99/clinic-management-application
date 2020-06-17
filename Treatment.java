import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Treatment {
    private long id = 0L;
    private String docName = null;
    private String description = null;
    private long complaintId = 0L;

    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Treatment() {}

    public Treatment(String docName, String description, long complaintId) {
        this.docName = docName;
        this.description = description;
        this.complaintId = complaintId;
    }

    public String toString() {
        String message = "";
        message += "Treatment ID: " + id + "\n";
        message += "Doctor Name: " + docName + "\n";
        message += "Complaint ID: " + complaintId + "\n";
        message += "Description: " + description;
        return message;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }

    public void setDocName(String docName) { this.docName = docName; }
    public String getDocName() { return docName; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setComplaintId(long complaintId) { this.complaintId = complaintId; }
    public long getComplaintId() { return complaintId; }

    public static long addTreatment(Treatment treatment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("INSERT INTO Treatment VALUES (0, ?, ?, ?, ?);");
            preparedStatement.setString(1, treatment.getDocName());
            preparedStatement.setString(2, treatment.getDescription());
            preparedStatement.setLong(3, treatment.getComplaintId());
            preparedStatement.setString(4, dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("SELECT MAX(id) FROM Treatment;");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                treatment.setId(id);
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

    public static boolean removeTreatment(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("DELETE FROM Treatment WHERE id = ?;");
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

    public static boolean updateTreatment(Treatment treatment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("UPDATE Treatment SET doc_name = ?, description = ?, complaint_id = ? WHERE id = ?;");
            preparedStatement.setString(1, treatment.getDocName());
            preparedStatement.setString(2, treatment.getDescription());
            preparedStatement.setLong(3, treatment.getComplaintId());
            preparedStatement.setLong(4, treatment.getId());
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

    public static boolean isTreatmentPresent(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Treatment WHERE id = ?;");
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

    public static Treatment getTreatment(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Treatment treatment = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Treatment WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                treatment = new Treatment();
                treatment.setId(resultSet.getLong(1));
                treatment.setDocName(resultSet.getString(2));
                treatment.setDescription(resultSet.getString(3));
                treatment.setComplaintId(resultSet.getLong(4));
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
        return treatment;
    }

    public static Treatment getTreatmentWithComplaintId(long complaintId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Treatment treatment = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Treatment WHERE complaint_id = ?;");
            preparedStatement.setLong(1, complaintId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                treatment = new Treatment();
                treatment.setId(resultSet.getLong(1));
                treatment.setDocName(resultSet.getString(2));
                treatment.setDescription(resultSet.getString(3));
                treatment.setComplaintId(resultSet.getLong(4));
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
        return treatment;
    }

    public static Treatment[] getTreatments(long patientId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Treatment[] treatments = new Treatment[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Treatment t, Complaint c WHERE t.complaint_id = c.id AND c.patient_id = ?;");
            preparedStatement.setLong(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Treatment> resultTreatment = new ArrayList<>();
            while (resultSet.next()) {
                Treatment treatment = new Treatment();
                treatment.setId(resultSet.getLong(1));
                treatment.setDocName(resultSet.getString(2));
                treatment.setDescription(resultSet.getString(3));
                treatment.setComplaintId(resultSet.getLong(4));
                resultTreatment.add(treatment);
            }
            if (resultTreatment.size() > 0) {
                treatments = resultTreatment.toArray(treatments);
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
        return treatments;
    }
}