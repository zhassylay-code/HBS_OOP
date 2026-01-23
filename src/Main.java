import Controller.BookingController;
import Controller.GuestController;
import UI.ConsoleMenu;
import repository.BookingRepository;
import repository.GuestRepository;
import repository.RoomRepository;
import repository.jdbc.JdbcBookingRepository;
import repository.jdbc.JdbcGuestRepository;
import repository.jdbc.JdbcRoomRepository;
import service.BookingService;

public class Main {
    public static void main(String[] args) {

   
        RoomRepository roomRepo = new JdbcRoomRepository();
        BookingRepository bookingRepo = new JdbcBookingRepository();
        GuestRepository guestRepo = new JdbcGuestRepository();

  
        BookingService bookingService = new BookingService(bookingRepo, roomRepo);

   
        BookingController bookingController = new BookingController(bookingService, roomRepo, bookingRepo);
        GuestController guestController = new GuestController(guestRepo);

   
        ConsoleMenu menu = new ConsoleMenu(bookingController, guestController);
        menu.start();
    }
}
