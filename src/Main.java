import controller.BookingController;
import controller.GuestController;
import ui.ConsoleMenu;
import db.DatabaseConnection;
import repository.BookingRepository;

import repository.*;
import service.BookingService;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();

            RoomRepository roomRepo = new RoomRepositoryImpl(conn);
            BookingRepository bookingRepo = new BookingRepositoryImpl(conn);
            GuestRepository guestRepo = new GuestRepositoryImpl(conn);

            BookingService bookingService = new BookingService(bookingRepo, roomRepo);

            BookingController bookingController =
                    new BookingController(bookingService, roomRepo, bookingRepo, guestRepo);


            GuestController guestController =
                    new GuestController(guestRepo, bookingRepo);

            ConsoleMenu menu = new ConsoleMenu(bookingController, guestController);
            menu.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
