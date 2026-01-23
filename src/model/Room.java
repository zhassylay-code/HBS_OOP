package model;
public class Room {
    private int id;
    private String roomNumber;
    private String type;
    private double pricePerNight;

    public Room(int id, String roomNumber, String type, double pricePerNight) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }
    public int getId() {
        return id;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public String getType() {
        return type;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
}
