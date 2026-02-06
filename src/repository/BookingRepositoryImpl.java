package repository;

import entity.Booking;

import java.sql.*;
import java.time.LocalDate;

public class BookingRepositoryImpl implements BookingRepository {

    private final Connection conn;

    public BookingRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean isRoomBusy(Long roomId, LocalDate start, LocalDate end) {
        String sql = """
            SELECT EXISTS (
                SELECT 1
                FROM bookings
                WHERE room_id = ?
                  AND start_date < ?
                  AND end_date > ?
            )
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, roomId);
            ps.setDate(2, Date.valueOf(end));   // new end
            ps.setDate(3, Date.valueOf(start)); // new start

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if room is busy", e);
        }
    }

    @Override
    public void save(Booking booking) {
        String sql = """
            INSERT INTO bookings (room_id, start_date, end_date)
            VALUES (?, ?, ?)
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, booking.roomId);
            ps.setDate(2, Date.valueOf(booking.startDate));
            ps.setDate(3, Date.valueOf(booking.endDate));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving booking", e);
        }
    }

    @Override
    public void deleteById(Long bookingId) {
        String sql = "DELETE FROM bookings WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, bookingId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting booking", e);
        }
    }

}
