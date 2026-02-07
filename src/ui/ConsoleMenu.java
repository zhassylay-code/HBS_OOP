package ui;

import controller.BookingController;
import controller.GuestController;
import exception.InvalidBookingDatesException;
import exception.RoomAlreadyBookedException;
import entity.Booking;

import java.time.LocalDate;
import java.util.Scanner;
import java.math.BigDecimal;
import java.util.List;


public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final BookingController bookingController;
    private final GuestController guestController;

    public ConsoleMenu(BookingController bookingController, GuestController guestController) {
        this.bookingController = bookingController;
        this.guestController = guestController;
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
        System.out.println("Welcome to the Grand hotel! How can we help you?");

        while (true) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> bookRoom();
                case 2 -> {
                    System.out.print("Enter your guest ID: ");
                    long guestId = readInt();
                    guestController.showProfile(guestId);
                }
                case 3 -> cancelBooking();
                case 4 -> {
                    System.out.println("Thank you for choosing our Grand hotel! We would be glad to see you again!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n1. I want to book a room");
        System.out.println("2. I want to see my profile");
        System.out.println("3. Cancel booking");
        System.out.println("4. Exit");

        System.out.print("Your choice: ");
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
            System.out.println("Stay: " + "from "+ startDate + " to " + endDate);
            System.out.println("Total price: " + price + " KZT");
            System.out.println("Payment method: " + paymentMethodToString(paymentChoice));

            bookingController.bookRoom(
                    roomId,
                    fullName,
                    document,
                    startDate,
                    endDate
            );


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
        List<Booking> bookings = bookingController.showAllBookings();


        if (bookings.isEmpty()) {
            return;
        }

        System.out.print("Enter booking ID to cancel: ");
        long bookingId = readInt();

        bookingController.cancelBooking(bookingId);
    }

}
