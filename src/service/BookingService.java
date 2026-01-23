package service; 

import entity.Booking;
import entity.Room;
import exception.InvalidBookingDatesException;
import exception.RoomAlreadyBookedException;
import repository.BookingRepository;
import repository.RoomRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit; 

public class BookingService {

    private BookingRepository bookingRepo;
    private RoomRepository roomRepo;

    public BookingService(BookingRepository b, RoomRepository r) {
        this.bookingRepo = b;
        this.roomRepo = r;
    }

    public BigDecimal bookRoom(Room room, LocalDate start, LocalDate end) {

        if (!start.isBefore(end)) {
            throw new InvalidBookingDatesException("Wrong dates");
        }

        if (bookingRepo.isRoomBusy(room.id, start, end)) {
            throw new RoomAlreadyBookedException("Room is busy");
        }

        long days = ChronoUnit.DAYS.between(start, end);
        BigDecimal total = room.pricePerNight.multiply(BigDecimal.valueOf(days));

        Booking booking = new Booking();
        booking.roomId = room.id;
        booking.startDate = start;
        booking.endDate = end;

        bookingRepo.save(booking);

        return total;
    }
}
