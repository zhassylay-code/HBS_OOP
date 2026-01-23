import UI.ConsoleMenu;
import Controller.BookingController;
import Controller.GuestController;

public class Main {
    public static void main(String[] args) {
        BookingController bookingController = new BookingController();
        GuestController guestController = new GuestController();

        ConsoleMenu menu = new ConsoleMenu(bookingController, guestController);
        menu.start();
    }
}
