package controller;

import entity.Room;
import entity.Booking;
import repository.BookingRepository;
import repository.RoomRepository;
import service.BookingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookingController {
    private final BookingService bookingService;
    private final RoomRepository roomRepo;
    private final BookingRepository bookingRepo;

    public BookingController(BookingService bookingService,
                             RoomRepository roomRepo,
                             BookingRepository bookingRepo) {
        this.bookingService = bookingService;
        this.roomRepo = roomRepo;
        this.bookingRepo = bookingRepo;
    }

    public BigDecimal getPrice(int roomId, LocalDate start, LocalDate end) {
        Room room = roomRepo.findById((long) roomId);

        if (room == null) {
            return BigDecimal.ZERO;
        }

        return bookingService.calculatePrice(room, start, end);
    }


    public void showAvailableRooms(LocalDate startDate, LocalDate endDate) {
        List<Room> rooms = roomRepo.findAll();

        List<Room> availableRooms = rooms.stream()
                .filter(room -> !bookingRepo.isRoomBusy(room.id, startDate, endDate))
                .toList();

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("%-10s %-25s %-15s%n", "ROOM ID", "TYPE", "PRICE (KZT)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        availableRooms.forEach(room ->
                System.out.printf(
                        "%-10d %-25s %-15s%n",
                        room.id,
                        room.type,
                        room.pricePerNight
                )
        );

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms for selected dates.");
        }
    }


    public void bookRoom(int roomId, LocalDate start, LocalDate end) {
        Room room = roomRepo.findById((long) roomId);

        if (room == null) {
            System.out.println("Room with ID " + roomId + " not found.");
            return;
        }


        bookingService.bookRoom(room, start, end);
    }
    public void cancelBooking(long bookingId) {
        bookingService.cancelBooking(bookingId);
        System.out.println("Booking has been successfully cancelled.");
    }

    public List<Booking> showAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return bookings;
        }

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf(
                "%-12s %-10s %-15s %-15s%n",
                "BOOKING ID",
                "ROOM ID",
                "START DATE",
                "END DATE"
        );
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        bookings.forEach(b ->
                System.out.printf(
                        "%-12d %-10d %-15s %-15s%n",
                        b.id,
                        b.roomId,
                        b.startDate,
                        b.endDate
                )
        );

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━2");

        return bookings;
    }



}
