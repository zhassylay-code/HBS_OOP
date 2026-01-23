package UI;

import Controller.BookingController;
import Controller.GuestController;

import java.time.LocalDate;
import java.util.Scanner;
import java.math.BigDecimal;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final BookingController bookingController;
    private final GuestController guestController;

    public ConsoleMenu(BookingController bookingController, GuestController guestController) {
        this.bookingController = bookingController;
        this.guestController = guestController;
    }

    public void start() {
        System.out.println("Welcome to the Grand hotel! How can we help you?");

        while (true) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> bookRoom();
                case 2 -> guestController.showProfile();
                case 3 -> {
                    System.out.println("Thank you for choosing our hotel! We would glad to see you again!.");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again(");
            }
        }
    }
    private boolean askToContinue() {
        System.out.println("\nDo you want to continue?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Your choice: ");

        int choice = readInt();
        return choice == 1;
    }


    private void printMenu() {
        System.out.println("1. I want to book a room");
        System.out.println("2. I want to see my profile");
        System.out.println("3. Exit");
        System.out.print("Your choice: ");
    }

    private void bookRoom() {
        try {
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            LocalDate today = LocalDate.now();

            if (startDate.isBefore(today)) {
                System.out.println("Check-in date cannot be in the past.");
                return;
            }

            if (endDate.isBefore(startDate)) {
                System.out.println("Check-out date cannot be before check-in date.");
                return;
            }

            System.out.println("Available rooms:");
            bookingController.showAvailableRooms(startDate, endDate);

            System.out.print("Enter room ID: ");
            int roomId = readInt();

            System.out.print("Please enter your full name: ");
            String fullName = scanner.nextLine();

            if (fullName.isEmpty()) {
                System.out.println("This field cannot be empty.");
                return;
            }

            System.out.print("Enter your passport number: ");
            String document = scanner.nextLine();

            if (document.isEmpty()) {
                System.out.println("This field cannot be empty.");
                return;
            }

            BigDecimal price = bookingController.getPrice(roomId, startDate, endDate);
            System.out.println("Total price is: " + price);

            System.out.println("Please choose payment method:");
            System.out.println("1. Card");
            System.out.println("2. Cash");
            System.out.print("Your choice: ");
            int paymentChoice = readInt();

            if (paymentChoice != 1 && paymentChoice != 2) {
                System.out.println("Invalid payment method.");
                return;
            }

            bookingController.bookRoom(roomId, startDate, endDate);

            System.out.println("Booking request has been successfully created. We are waiting for you!");

            if (!askToContinue()) {
                System.out.println("Thank you for choosing our hotel! We look forward to welcoming you again.");
                System.exit(0);
            }

        }

        catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }


    }

    private int readInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
