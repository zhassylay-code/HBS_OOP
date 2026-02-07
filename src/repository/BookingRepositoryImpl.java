package repository;

import entity.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                FROM bookings b
                JOIN rooms r ON b.room_id = r.id
                WHERE r.id = ?
                  AND b.start_date < ?
                  AND b.end_date > ?
            )
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, roomId);
            ps.setDate(2, Date.valueOf(end));
            ps.setDate(3, Date.valueOf(start));

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

    @Override
    public List<Booking> findAll() {


        String sql = """
            SELECT b.id, b.room_id, b.start_date, b.end_date
            FROM bookings b
            ORDER BY b.start_date
            """;

        List<Booking> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking b = new Booking();
                b.id = rs.getLong("id");
                b.roomId = rs.getLong("room_id");
                b.startDate = rs.getDate("start_date").toLocalDate();
                b.endDate = rs.getDate("end_date").toLocalDate();
                list.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
