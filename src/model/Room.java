package model;
public class Room {
    private int id;
    private String type;
    private int capacity;
    private double price;
    private boolean isAvailable;

    public Room() {}

    public Room(String type, int capacity, double price, boolean isAvailable) {
        this.type = type;
        this.capacity = capacity;
        this.price = price;
        this.isAvailable = isAvailable;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
