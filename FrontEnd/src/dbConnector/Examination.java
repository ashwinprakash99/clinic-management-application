package dbConnector;

import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Examination {
    private long id = 0L;
    private String bp = "";
    private String pulse = "";
    private String temperature = "";
    private String cvs = "";
    private String rs = "";
    private String pa = "";
    private String cns = "";
    private String labtest = "";
    private String ecg = "";
    private String xray = "";
    private String ctScanMri = "";
    private String twoDEcho = "";
    private String tmt = "";
    private String eeg = "";
    private String diagnosis = "";
    private String other = "";
    private long complaintId = 0L;

    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Examination() {}

    public Examination(long complaintId, String bp, String pulse, String temperature, String cvs, String rs, String pa, String cns, String labtest, String ecg, String xray, String ctScanMri, String twoDEcho, String tmt, String eeg, String diagnosis, String other) {
        this.complaintId = complaintId;
        this.bp = bp;
        this.pulse = pulse;
        this.temperature = temperature;
        this.cvs = cvs;
        this.rs = rs;
        this.pa = pa;
        this.cns = cns;
        this.labtest = labtest;
        this.ecg = ecg;
        this.xray = xray;
        this.ctScanMri = ctScanMri;
        this.twoDEcho = twoDEcho;
        this.tmt = tmt;
        this.eeg = eeg;
        this.diagnosis = diagnosis;
        this.other = other;
    }

    public String toString() {
        String message = "";
        message += "Examination ID: " + id + "\n";
        message += "Complaint ID: " + complaintId + "\n";
        message += "BP: " + bp + "\n";
        message += "Pulse: " + pulse + "\n";
        message += "Temperature: " + temperature + "\n";
        message += "CVS: " + cvs + "\n";
        message += "RS: " + rs + "\n";
        message += "PA: " + pa + "\n";
        message += "CNS: " + cns + "\n";
        message += "Labtest: " + labtest + "\n";
        message += "ECG: " + ecg + "\n";
        message += "X Ray: " + xray + "\n";
        message += "CT Scan / MRI: " + ctScanMri + "\n";
        message += "Two D Echo: " + twoDEcho + "\n";
        message += "TMT: " + tmt + "\n";
        message += "EEG: " + eeg + "\n";
        message += "Diagnosis: " + diagnosis + "\n";
        message += "Other: " + other;
        return message;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }

    public void setComplaintId(long complaintId) { this.complaintId = complaintId; }
    public long getComplaintId() { return complaintId; }

    public void setBp(String bp) { this.bp = bp; }
    public String getBp() { return bp; }

    public void setPulse(String pulse) { this.pulse = pulse; }
    public String getPulse() { return pulse; }

    public void setTemperature(String temperature) { this.temperature = temperature; }
    public String getTemperature() { return temperature; }

    public void setCvs(String cvs) { this.cvs = cvs; }
    public String getCvs() { return cvs; }

    public void setRs(String rs) { this.rs = rs; }
    public String getRs() { return rs; }

    public void setPa(String pa) { this.pa = pa; }
    public String getPa() { return pa; }

    public void setCns(String cns) { this.cns = cns; }
    public String getCns() { return cns; }

    public void setLabtest(String labtest) { this.labtest = labtest; }
    public String getLabtest() { return labtest; }

    public void setEcg(String ecg) { this.ecg = ecg; }
    public String getEcg() { return ecg; }

    public void setXray(String xray) { this.xray = xray; }
    public String getXray() { return xray; }

    public void setCtScanMri(String ctScanMri) { this.ctScanMri = ctScanMri; }
    public String getCtScanMri() { return ctScanMri; }

    public void setTwoDEcho(String twoDEcho) { this.twoDEcho = twoDEcho; }
    public String getTwoDEcho() { return twoDEcho; }

    public void setTmt(String tmt) { this.tmt = tmt; }
    public String getTmt() { return tmt; }

    public void setEeg(String eeg) { this.eeg = eeg; }
    public String getEeg() { return eeg; }

    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getDiagnosis() { return diagnosis; }

    public void setOther(String other) { this.other = other; }
    public String getOther() { return other; }

    public static long addExamination(Examination examination) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("INSERT INTO Examination VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, examination.getBp());
            preparedStatement.setString(2, examination.getPulse());
            preparedStatement.setString(3, examination.getTemperature());
            preparedStatement.setString(4, examination.getCvs());
            preparedStatement.setString(5, examination.getRs());
            preparedStatement.setString(6, examination.getPa());
            preparedStatement.setString(7, examination.getCns());
            preparedStatement.setString(8, examination.getLabtest());
            preparedStatement.setString(9, examination.getEcg());
            preparedStatement.setString(10, examination.getXray());
            preparedStatement.setString(11, examination.getCtScanMri());
            preparedStatement.setString(12, examination.getTwoDEcho());
            preparedStatement.setString(13, examination.getTmt());
            preparedStatement.setString(14, examination.getEeg());
            preparedStatement.setString(15, examination.getDiagnosis());
            preparedStatement.setString(16, examination.getOther());
            preparedStatement.setLong(17, examination.getComplaintId());
            preparedStatement.setString(18, dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = MainDataConnection.connection.prepareStatement("SELECT MAX(id) FROM Examination;");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                examination.setId(id);
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

    public static boolean removeExamination(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("DELETE FROM Examination WHERE id = ?;");
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

    public static boolean updateExamination(Examination examination) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("UPDATE Examination SET bp = ?, pulse = ?, temperature = ?, cvs = ?, rs = ?, pa = ?, cns = ?, labtest = ?, ecg = ?, x_ray = ?, ct_scan_mri = ?, two_d_echo = ?, tmt = ?, eeg = ?, diagnosis = ?, other = ?, complaint_id = ? WHERE id = ?;");
            preparedStatement.setString(1, examination.getBp());
            preparedStatement.setString(2, examination.getPulse());
            preparedStatement.setString(3, examination.getTemperature());
            preparedStatement.setString(4, examination.getCvs());
            preparedStatement.setString(5, examination.getRs());
            preparedStatement.setString(6, examination.getPa());
            preparedStatement.setString(7, examination.getCns());
            preparedStatement.setString(8, examination.getLabtest());
            preparedStatement.setString(9, examination.getEcg());
            preparedStatement.setString(10, examination.getXray());
            preparedStatement.setString(11, examination.getCtScanMri());
            preparedStatement.setString(12, examination.getTwoDEcho());
            preparedStatement.setString(13, examination.getTmt());
            preparedStatement.setString(14, examination.getEeg());
            preparedStatement.setString(15, examination.getDiagnosis());
            preparedStatement.setString(16, examination.getOther());
            preparedStatement.setLong(17, examination.getComplaintId());
            preparedStatement.setLong(18, examination.getId());
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

    public static boolean isExaminationPresent(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT * FROM Examination WHERE id = ?;");
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

    public static Examination getExamination(long id) {
        PreparedStatement preparedStatement = null;
        Examination examination = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT * FROM Examination WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                examination = new Examination();
                examination.setId(resultSet.getLong(1));
                examination.setBp(resultSet.getString(2));
                examination.setPulse(resultSet.getString(3));
                examination.setTemperature(resultSet.getString(4));
                examination.setCvs(resultSet.getString(5));
                examination.setRs(resultSet.getString(6));
                examination.setPa(resultSet.getString(7));
                examination.setCns(resultSet.getString(8));
                examination.setLabtest(resultSet.getString(9));
                examination.setEcg(resultSet.getString(10));
                examination.setXray(resultSet.getString(11));
                examination.setCtScanMri(resultSet.getString(12));
                examination.setTwoDEcho(resultSet.getString(13));
                examination.setTmt(resultSet.getString(14));
                examination.setEeg(resultSet.getString(15));
                examination.setDiagnosis(resultSet.getString(16));
                examination.setOther(resultSet.getString(17));
                examination.setComplaintId(resultSet.getLong(18));
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
        return examination;
    }

    public static Examination getExaminationWithComplaintId(long complaintId) {
        PreparedStatement preparedStatement = null;
        Examination examination = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT * FROM Examination WHERE complaint_id = ?;");
            preparedStatement.setLong(1, complaintId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                examination = new Examination();
                examination.setId(resultSet.getLong(1));
                examination.setBp(resultSet.getString(2));
                examination.setPulse(resultSet.getString(3));
                examination.setTemperature(resultSet.getString(4));
                examination.setCvs(resultSet.getString(5));
                examination.setRs(resultSet.getString(6));
                examination.setPa(resultSet.getString(7));
                examination.setCns(resultSet.getString(8));
                examination.setLabtest(resultSet.getString(9));
                examination.setEcg(resultSet.getString(10));
                examination.setXray(resultSet.getString(11));
                examination.setCtScanMri(resultSet.getString(12));
                examination.setTwoDEcho(resultSet.getString(13));
                examination.setTmt(resultSet.getString(14));
                examination.setEeg(resultSet.getString(15));
                examination.setDiagnosis(resultSet.getString(16));
                examination.setOther(resultSet.getString(17));
                examination.setComplaintId(resultSet.getLong(18));
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
        return examination;
    }

    public static Examination[] getExaminations(long patientId) {
        PreparedStatement preparedStatement = null;
        Examination[] examinations = new Examination[0];
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT e.id,e.bp,e.pulse,e.temperature,e.cvs,e.rs,e.pa,e.cns,e.labtest,e.ecg,e.x_ray,e.ct_scan_mri,e.two_d_echo,e.tmt,e.eeg,e.diagnosis,e.other,e.complaint_id FROM Examination e, Complaint c WHERE e.complaint_id = c.id AND c.patient_id = ?;");
            preparedStatement.setLong(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Examination> resultExamination = new ArrayList<>();
            while (resultSet.next()) {
                Examination examination = new Examination();
                examination.setId(resultSet.getLong(1));
                examination.setBp(resultSet.getString(2));
                examination.setPulse(resultSet.getString(3));
                examination.setTemperature(resultSet.getString(4));
                examination.setCvs(resultSet.getString(5));
                examination.setRs(resultSet.getString(6));
                examination.setPa(resultSet.getString(7));
                examination.setCns(resultSet.getString(8));
                examination.setLabtest(resultSet.getString(9));
                examination.setEcg(resultSet.getString(10));
                examination.setXray(resultSet.getString(11));
                examination.setCtScanMri(resultSet.getString(12));
                examination.setTwoDEcho(resultSet.getString(13));
                examination.setTmt(resultSet.getString(14));
                examination.setEeg(resultSet.getString(15));
                examination.setDiagnosis(resultSet.getString(16));
                examination.setOther(resultSet.getString(17));
                examination.setComplaintId(resultSet.getLong(18));
                resultExamination.add(examination);
            }
            if (resultExamination.size() > 0) {
                examinations = resultExamination.toArray(examinations);
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
        return examinations;
    }
}