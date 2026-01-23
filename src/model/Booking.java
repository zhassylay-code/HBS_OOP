package model;
import java.time.LocalDate;
public class Booking {
    private int id;
    private int roomId;
    private int guestId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking() {}

    public Booking(int roomId, int guestId, LocalDate startDate, LocalDate endDate) {
        this.roomId = roomId;
        this.guestId = guestId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", guestId=" + guestId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
