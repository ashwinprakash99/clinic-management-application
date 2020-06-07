import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Patient {
    private long UHID;
    private String patientName;
    private Date DOB;
    private char gender;
    private String address;
    private long phoneNumber;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public void setUHID(long UHID) { this.UHID = UHID; }
    public long getUHID() { return this.UHID; }

    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getPatientName() { return this.patientName; }

    public void setDOB(String DOB) throws ParseException { this.DOB = df.parse(DOB); }
    public void setDOB(Date DOB) { this.DOB = DOB; }
    public Date getDOB() { return this.DOB; }

    public void setGender(char gender) { this.gender = gender; }
    public char getGender() { return this.gender; }

    public void setAddress(String address) { this.address = address; }
    public String getAddress() { return this.address; }

    public void setPhoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }
    public long getPhoneNumber() { return this.phoneNumber; }
}
