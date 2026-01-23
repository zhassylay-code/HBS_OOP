package model;
public class Guest {
    private int id;
    private String fullName;
    private String phone;

    public Guest(int id, String fullName, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public String getPhone() {
        return phone;
    }
}

