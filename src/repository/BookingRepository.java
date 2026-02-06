package repository;

import entity.Booking;
import java.time.LocalDate;

public interface BookingRepository {
    boolean isRoomBusy(Long roomId, LocalDate start, LocalDate end);
    void save(Booking booking);

}
