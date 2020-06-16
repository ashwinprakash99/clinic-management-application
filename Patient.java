import java.util.Date;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Patient {
    private long UHID = 0L;
    private String patientName = null;
    private Date DOB = null;
    private String gender = null;
    private String address = null;
    private long phoneNumber = 0L;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Patient() {}

    public Patient(String patientName, String DOB, String gender, String address, long phoneNumber) throws ParseException {
        this.patientName = patientName;
        this.DOB = df.parse(DOB);
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Patient(String patientName, Date DOB, String gender, String address, long phoneNumber) throws ParseException {
        this.patientName = patientName;
        this.DOB = DOB;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        String message = "";
        message += "UHID: " + UHID + "\n";
        message += "Patient Name: " + patientName + "\n";
        message += "DOB: " + DOB + "\n";
        message += "Gender: " + gender + "\n";
        message += "Address: " + address + "\n";
        message += "Number: " + phoneNumber;
        return message;
    }

    public void setUHID(long UHID) { this.UHID = UHID; }
    public long getUHID() { return this.UHID; }

    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getPatientName() { return this.patientName; }

    public void setDOB(String DOB) throws ParseException { this.DOB = df.parse(DOB); }
    public void setDOB(Date DOB) { this.DOB = DOB; }
    public String getDOB() { return df.format(this.DOB); }

    public void setGender(String gender) { this.gender = gender; }
    public String getGender() { return this.gender; }

    public void setAddress(String address) { this.address = address; }
    public String getAddress() { return this.address; }

    public void setPhoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }
    public long getPhoneNumber() { return this.phoneNumber; }

    public static long addPatient(Patient patient) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("INSERT INTO Patient VALUES (0, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, patient.getPatientName());
            preparedStatement.setString(2, patient.getDOB());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getAddress());
            preparedStatement.setLong(5, patient.getPhoneNumber());
            preparedStatement.setString(6, dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = connection.prepareStatement("SELECT MAX(UHID) FROM Patient;");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long UHID = resultSet.getLong(1);
                patient.setUHID(UHID);
                return UHID;
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

    public static boolean removePatient(long UHID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("DELETE FROM Patient WHERE UHID = ?;");
            preparedStatement.setLong(1, UHID);
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

    public static boolean updatePatient(Patient patient) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("UPDATE Patient SET patient_name = ?, DOB = ?, gender = ?, address = ?, phone_number = ? WHERE UHID = ?;");
            preparedStatement.setString(1, patient.getPatientName());
            preparedStatement.setString(2, patient.getDOB());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getAddress());
            preparedStatement.setLong(5, patient.getPhoneNumber());
            preparedStatement.setLong(6, patient.getUHID());
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

    public static boolean isPatientPresent(long UHID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Patient WHERE UHID = ?;");
            preparedStatement.setLong(1, UHID);
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

    public static Patient getPatient(long UHID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient patient = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Patient WHERE UHID = ?;");
            preparedStatement.setLong(1, UHID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                patient = new Patient();
                patient.setUHID(resultSet.getLong(1));
                patient.setPatientName(resultSet.getString(2));
                patient.setDOB(resultSet.getString(3));
                patient.setGender(resultSet.getString(4));
                patient.setAddress(resultSet.getString(5));
                patient.setPhoneNumber(resultSet.getLong(6));
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
        return patient;
    }

    public static Patient[] getPatients(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient[] patients = new Patient[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Patient WHERE UPPER(patient_name) LIKE ?;");
            preparedStatement.setString(1, "%"+name.toUpperCase()+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Patient> resultPatient = new ArrayList<>();
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setUHID(resultSet.getLong(1));
                patient.setPatientName(resultSet.getString(2));
                patient.setDOB(resultSet.getString(3));
                patient.setGender(resultSet.getString(4));
                patient.setAddress(resultSet.getString(5));
                patient.setPhoneNumber(resultSet.getLong(6));
                resultPatient.add(patient);
            }
            if (resultPatient.size() > 0) {
                patients = resultPatient.toArray(patients);
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
        return patients;
    }

    public static Patient[] getAllPatients() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient[] patients = new Patient[0];
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull...");
            preparedStatement = connection.prepareStatement("SELECT * FROM Patient;");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Patient> resultPatient = new ArrayList<>();
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setUHID(resultSet.getLong(1));
                patient.setPatientName(resultSet.getString(2));
                patient.setDOB(resultSet.getString(3));
                patient.setGender(resultSet.getString(4));
                patient.setAddress(resultSet.getString(5));
                patient.setPhoneNumber(resultSet.getLong(6));
                resultPatient.add(patient);
            }
            if (resultPatient.size() > 0) {
                patients = resultPatient.toArray(patients);
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
        return patients;
    }
}
