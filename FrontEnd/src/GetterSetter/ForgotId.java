package GetterSetter;

public class ForgotId {

    Long id;
    String name;
    Long Phone;

    public ForgotId(Long id, String name, Long phone) {
        this.id = id;
        this.name = name;
        Phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return Phone;
    }

    public void setPhone(Long phone) {
        Phone = phone;
    }
}
