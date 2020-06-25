package GetterSetter;

public class AllPatientDetails {
    Long uhid;
    String name,dob,gender,address;
    Long phone;

    public AllPatientDetails(Long uhid, String name, String dob, String gender, String address, Long phone) {
        this.uhid = uhid;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public Long getUhid() {
        return uhid;
    }

    public void setUhid(Long uhid) {
        this.uhid = uhid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
