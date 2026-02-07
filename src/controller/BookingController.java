package controller;

import entity.Room;
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

        boolean any = false;

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("%-10s %-25s %-15s%n", "ROOM ID", "TYPE", "PRICE (KZT)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        rooms.stream()
                .filter(room -> !bookingRepo.isRoomBusy(room.id, startDate, endDate))
                .forEach(room -> {
                    System.out.printf(
                            "%-10d %-25s %-15s%n",
                            room.id,
                            room.type,
                            room.pricePerNight
                    );
                });


        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        if (!any) {
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

}
