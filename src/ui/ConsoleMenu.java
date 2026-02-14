package ui;

import auth.Session;
import controller.BookingController;
import controller.GuestController;
import controller.RoomController;
import entity.Booking;
import entity.Role;
import exception.InvalidBookingDatesException;
import exception.RoomAlreadyBookedException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final BookingController bookingController;
    private final GuestController guestController;
    private final RoomController roomController;

    public ConsoleMenu(
            BookingController bookingController,
            GuestController guestController,
            RoomController roomController
    ) {
        this.bookingController = bookingController;
        this.guestController = guestController;
        this.roomController = roomController;
    }
    private void exit(){
        System.out.println("Thank you for choosing our Grand hotel! We would be glad to see you again!");
        return;
    }
    private void Header(String title) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(title.toUpperCase());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    private String paymentMethodToString(int choice) {
        return choice == 1 ? "Card" : "Cash";
    }

    public void start() {
        System.out.println("Welcome to the Grand Hotel!");

        loginMenu();

        while (true) {
            switch (Session.getRole()) {
                case USER -> userMenu();
                case MANAGER -> managerMenu();
                case ADMIN -> adminMenu();
            }
        }
    }

    private void userMenu() {
        Header("Menu:");
        System.out.println("1. I want to book a room");
        System.out.println("2. I want to view my profile");
        System.out.println("3. Exit");
        System.out.print("The choice: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> bookRoom();
            case 2 -> {
                System.out.print("Enter your guest ID: ");
                long guestId = readInt();
                guestController.showProfile(guestId);
            }
            case 3 -> exit();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void managerMenu() {
        Header("Menu:");
        System.out.println("1. Book a room for guest");
        System.out.println("2. View guest profile");
        System.out.println("3. Cancel booking");
        System.out.println("4. Exit");
        System.out.print("The choice: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> bookRoom();
            case 2 -> {
                System.out.print("Enter guest ID: ");
                long guestId = readInt();
                guestController.showProfile(guestId);
            }
            case 3 -> cancelBooking();
            case 4 -> exit();
            default -> System.out.println("Invalid option.");
        }
    }

    private void adminMenu() {
        Header("Menu: ");
        System.out.println("1. Book a room");
        System.out.println("2. View guest profile");
        System.out.println("3. Cancel booking");
        System.out.println("4. Add a room");
        System.out.println("5. Update a room price");
        System.out.println("6. Delete a room");
        System.out.println("7. Exit");
        System.out.print("The choice: ");

        int choice = readInt();

        switch (choice) {
            case 1 -> bookRoom();
            case 2 -> {
                System.out.print("Enter guest ID: ");
                long guestId = readInt();
                guestController.showProfile(guestId);
            }
            case 3 -> cancelBooking();
            case 4 -> addRoom();
            case 5 -> updateRoomPrice();
            case 6 -> deleteRoom();
            case 7 -> exit();
            default -> System.out.println("Invalid option.");
        }
    }


    private void loginMenu() {
        System.out.println("\nLogin type:");
        System.out.println("1. Guest");
        System.out.println("2. Admin / Manager");
        System.out.print("Your choice: ");

        int choice = readInt();

        if (choice == 1) {
            Session.loginUser();
            System.out.println("Logged in as USER.");
            return;
        }

        if (choice == 2) {
            System.out.println("1. Admin");
            System.out.println("2. Manager");
            System.out.print("Choose role: ");
            int roleChoice = readInt();

            Role role = (roleChoice == 1) ? Role.ADMIN : Role.MANAGER;

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            try {
                Session.loginAdminOrManager(role, password);
                System.out.println("Logged in as " + role + ".");
            } catch (SecurityException e) {
                System.out.println("Wrong password. Logged in as USER.");
                Session.loginUser();
            }
            return;
        }

        System.out.println("Invalid option. Logged in as USER.");
        Session.loginUser();
    }



    private void bookRoom() {
        try {
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine().trim());

            LocalDate today = LocalDate.now();

            if (startDate.isBefore(today)) {
                System.out.println("Check-in date cannot be in the past.");
                return;
            }

            if (!endDate.isAfter(startDate)) {
                System.out.println("Check-out date must be after check-in date.");
                return;
            }

            System.out.println("Available rooms");
            bookingController.showAvailableRooms(startDate, endDate);

            System.out.print("\nEnter room ID: ");
            int roomId = readInt();

            System.out.print("Please enter your full name: ");
            String fullName = scanner.nextLine().trim();
            if (fullName.isEmpty()) {
                System.out.println("This field cannot be empty!");
                return;
            }

            System.out.print("Enter your passport number: ");
            String document = scanner.nextLine().trim();
            if (document.isEmpty()) {
                System.out.println("This field cannot be empty!");
                return;
            }

            BigDecimal price = bookingController.getPrice(roomId, startDate, endDate);
            System.out.println("Total price is: " + price);

            System.out.println("\nPlease choose payment method:");
            System.out.println("1. Card");
            System.out.println("2. Cash");
            System.out.print("Your choice: ");
            int paymentChoice = readInt();

            if (paymentChoice != 1 && paymentChoice != 2) {
                System.out.println("Invalid payment method.");
                return;
            }

            Header("Booking confirmation:");
            System.out.println("Guest: " + fullName);
            System.out.println("Room ID: " + roomId);
            System.out.println("Stay: " + "from " + startDate + " to " + endDate);
            System.out.println("Total price: " + price + " KZT");
            System.out.println("Payment method: " + paymentMethodToString(paymentChoice));

            bookingController.bookRoom(roomId, fullName, document, startDate, endDate);

            System.out.println("\nBooking request has been successfully created. We are waiting for you!");

        } catch (RoomAlreadyBookedException e) {
            System.out.println("This room is already booked for selected dates. Please choose another room.");
        } catch (InvalidBookingDatesException e) {
            System.out.println("Wrong booking dates.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private int readInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private void cancelBooking() {
        try {
            List<Booking> bookings = bookingController.showAllBookings();
            if (bookings.isEmpty()) return;

            System.out.print("Enter booking ID to cancel: ");
            long bookingId = readInt();

            bookingController.cancelBooking(bookingId);

        } catch (SecurityException e) {
            System.out.println("Only ADMIN or MANAGER can cancel bookings.");
        }
    }

    private void addRoom() {
        try {
            System.out.print("Enter room type: ");
            String type = scanner.nextLine().trim();

            System.out.print("Enter price per night: ");
            BigDecimal price = new BigDecimal(scanner.nextLine().trim());

            roomController.addRoom(type, price);
        } catch (SecurityException e) {
            System.out.println("Only ADMIN can add rooms.");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private void updateRoomPrice() {
        try {
            System.out.print("Enter room ID: ");
            long id = readInt();

            System.out.print("Enter new price per night: ");
            BigDecimal price = new BigDecimal(scanner.nextLine().trim());

            roomController.updateRoomPrice(id, price);
        } catch (SecurityException e) {
            System.out.println("Only ADMIN can update room prices.");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private void deleteRoom() {
        try {
            System.out.print("Enter room ID: ");
            long id = readInt();

            roomController.deleteRoom(id);
        } catch (SecurityException e) {
            System.out.println("Only ADMIN can delete rooms.");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }
}
