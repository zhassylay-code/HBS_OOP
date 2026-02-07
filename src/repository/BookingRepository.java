package repository;

import entity.Booking;
import java.util.List;
import java.time.LocalDate;

public interface BookingRepository {
    boolean isRoomBusy(Long roomId, LocalDate start, LocalDate end);
    void save(Booking booking);
    void deleteById(Long bookingId);
    List<Booking> findAll();
}
