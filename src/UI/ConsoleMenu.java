package UI;
import Controller.BookingController;
import Controller.GuestController;
import java.util.Scanner;


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
        System.out.print("");
    }

    private int readInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
