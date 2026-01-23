package UI;
import Controller.BookingController;
import Controller.GuestController;
import java.util.Scanner;
import java.time.LocalDate;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final BookingController bookingController = new BookingController();
    private final GuestController guestController = new GuestController();

    public void start() {
    System.out.println("Welcome to the hotel! How can we help you?");

        while (true) {
            PrintMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> bookRoom();
                case 2 -> guestController.showProfile();
                case 3 -> {
                    System.out.println("Thank you for choosing our hotel! We look forward to welcoming you again.");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void PrintMenu() {
        System.out.println("1. I want to book a room");
        System.out.println("2. Let's see my profile");
        System.out.println("3. Exit");
        System.out.print("Your choice: ");
    }
    private void bookRoom() {
        System.out.print("Enter check-in date (YYYY_MM-DD): ");
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
        int roomId = scanner.nextInt();
        scanner.nextLine();


        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();
        if (fullName.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter your passport number: ");
        String document = scanner.nextLine();

        if (document.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        double price = bookingController.getPrice(roomId, startDate, endDate);
        System.out.println("Total price: " + price);

        System.out.println("Choose payment method:");
        System.out.println("1. Card");
        System.out.println("2. Cash");
        System.out.print("Your choice: ");


        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        if (paymentChoice != 1 && paymentChoice != 2) {
            System.out.println("Invalid payment method.");
            return;
        }

        System.out.println("\nBooking request has been successfully created.");
        //надо добавить хотите продолжить?

    }

    private int readInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
