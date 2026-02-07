package controller;

import entity.Booking;
import entity.Guest;
import repository.BookingRepository;
import repository.GuestRepository;
import ui.ConsoleMenu;


import java.util.List;

public class GuestController {

    private final GuestRepository guestRepo;
    private final BookingRepository bookingRepo;

    public GuestController(GuestRepository guestRepo, BookingRepository bookingRepo) {
        this.guestRepo = guestRepo;
        this.bookingRepo = bookingRepo;
    }

    public void showProfile(Long guestId) {
        Guest guest = guestRepo.findById(guestId);

        if (guest == null) {
            System.out.println("Guest not found.");
            return;
        }

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("GUEST PROFILE");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Full name : " + guest.fullName);
        System.out.println("Document  : " + guest.document);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        List<Booking> bookings = bookingRepo.findByGuestId(guestId);

        if (bookings.isEmpty()) {
            System.out.println("No bookings found for this guest.");
            return;
        }

        System.out.println("\nBOOKINGS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf(
                "%-12s %-10s %-15s %-15s%n",
                "BOOKING ID",
                "ROOM ID",
                "START DATE",
                "END DATE"
        );
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        bookings.forEach(b ->
                System.out.printf(
                        "%-12d %-10d %-15s %-15s%n",
                        b.id,
                        b.roomId,
                        b.startDate,
                        b.endDate
                )
        );

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
}
