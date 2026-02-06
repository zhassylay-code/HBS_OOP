import controller.BookingController;
import controller.GuestController;
import ui.ConsoleMenu;
import db.DatabaseConnection;

import repository.*;
import service.BookingService;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();

            RoomRepository roomRepo = new RoomRepositoryImpl(conn);
            BookingRepository bookingRepo = new BookingRepositoryImpl(conn);

            BookingService bookingService = new BookingService(bookingRepo, roomRepo);

            BookingController bookingController =
                    new BookingController(bookingService, roomRepo, bookingRepo);


            GuestController guestController = new GuestController(null);

            ConsoleMenu menu = new ConsoleMenu(bookingController, guestController);
            menu.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
