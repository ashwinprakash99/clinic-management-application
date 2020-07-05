package GetterSetter;

public class PreviousHistory {
    long compid;
    String comp1, comp2, comp3, examin1, examin2, examin3;
    long excompid, examid;
    String bp, pulse, temp, cvs, rs, pa, cns, lab, ecg, xray, ct, two, tmt, eeg, diag, other;
    long presecompid, presecid;
    String medName;
    int quantity;
    boolean morning, afternoon, night;


    public PreviousHistory(long compid, String comp1, String comp2, String comp3, String examin1, String examin2, String examin3) {
        this.compid = compid;
        this.comp1 = comp1;
        this.comp2 = comp2;
        this.comp3 = comp3;
        this.examin1 = examin1;
        this.examin2 = examin2;
        this.examin3 = examin3;
    }

    public PreviousHistory(long excompid, long examid, String bp, String pulse, String temp, String cvs, String rs, String pa, String cns, String lab, String ecg, String xray, String ct, String two, String tmt, String eeg, String diag, String other) {
        this.excompid = excompid;
        this.examid = examid;
        this.bp = bp;
        this.pulse = pulse;
        this.temp = temp;
        this.cvs = cvs;
        this.rs = rs;
        this.pa = pa;
        this.cns = cns;
        this.lab = lab;
        this.ecg = ecg;
        this.xray = xray;
        this.ct = ct;
        this.two = two;
        this.tmt = tmt;
        this.eeg = eeg;
        this.diag = diag;
        this.other = other;
    }

    public PreviousHistory(long presecompid, long presecid, String medName, int quantity, boolean morning, boolean afternoon, boolean night) {
        this.presecompid = presecompid;
        this.presecid = presecid;
        this.medName = medName;
        this.quantity = quantity;
        this.morning = morning;
        this.afternoon = afternoon;
        this.night = night;
    }


    public long getCompid() {
        return compid;
    }

    public void setCompid(long compid) {
        this.compid = compid;
    }

    public String getComp1() {
        return comp1;
    }

    public void setComp1(String comp1) {
        this.comp1 = comp1;
    }

    public String getComp2() {
        return comp2;
    }

    public void setComp2(String comp2) {
        this.comp2 = comp2;
    }

    public String getComp3() {
        return comp3;
    }

    public void setComp3(String comp3) {
        this.comp3 = comp3;
    }

    public String getExamin1() {
        return examin1;
    }

    public void setExamin1(String examin1) {
        this.examin1 = examin1;
    }

    public String getExamin2() {
        return examin2;
    }

    public void setExamin2(String examin2) {
        this.examin2 = examin2;
    }

    public String getExamin3() {
        return examin3;
    }

    public void setExamin3(String examin3) {
        this.examin3 = examin3;
    }

    public long getExcompid() {
        return excompid;
    }

    public void setExcompid(long excompid) {
        this.excompid = excompid;
    }

    public long getExamid() {
        return examid;
    }

    public void setExamid(long examid) {
        this.examid = examid;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getCvs() {
        return cvs;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getEcg() {
        return ecg;
    }

    public void setEcg(String ecg) {
        this.ecg = ecg;
    }

    public String getXray() {
        return xray;
    }

    public void setXray(String xray) {
        this.xray = xray;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getTmt() {
        return tmt;
    }

    public void setTmt(String tmt) {
        this.tmt = tmt;
    }

    public String getEeg() {
        return eeg;
    }

    public void setEeg(String eeg) {
        this.eeg = eeg;
    }

    public String getDiag() {
        return diag;
    }

    public void setDiag(String diag) {
        this.diag = diag;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public long getPresecompid() {
        return presecompid;
    }

    public void setPresecompid(long presecompid) {
        this.presecompid = presecompid;
    }

    public long getPresecid() {
        return presecid;
    }

    public void setPresecid(long presecid) {
        this.presecid = presecid;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isNight() {
        return night;
    }

    public void setNight(boolean night) {
        this.night = night;
    }
}


