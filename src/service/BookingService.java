package service; //Business logic

import entity.Booking;
import entity.Room;
import exception.InvalidBookingDatesException;
import exception.RoomAlreadyBookedException;
import repository.BookingRepository;
import repository.RoomRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit; //used to calculate number of days between dates(java)

public class BookingService {

