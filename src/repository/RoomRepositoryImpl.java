package repository;

import entity.Room;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private final Connection conn;

    public RoomRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Room findById(Long id) {
        String sql = """
            SELECT id, type, price_per_night
            FROM rooms
            WHERE id = ?
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            Room room = new Room();
            room.id = rs.getLong("id");
            room.type = rs.getString("type");
            room.pricePerNight = rs.getBigDecimal("price_per_night");
            return room;

        } catch (SQLException e) {
            throw new RuntimeException("Error finding room by id", e);
        }
    }

    @Override
    public List<Room> findAll() {
        String sql = """
            SELECT id, type, price_per_night
            FROM rooms
            """;

        List<Room> rooms = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room room = new Room();
                room.id = rs.getLong("id");
                room.type = rs.getString("type");
                room.pricePerNight = rs.getBigDecimal("price_per_night");
                rooms.add(room);
            }

            return rooms;

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all rooms", e);
        }
    }

    @Override
    public void save(Room room) {
        String sql = """
            INSERT INTO rooms (type, price_per_night)
            VALUES (?, ?)
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, room.type);
            ps.setBigDecimal(2, room.pricePerNight);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving room", e);
        }
    }

    @Override
    public void updatePrice(Long id, BigDecimal newPrice) {
        String sql = """
            UPDATE rooms
            SET price_per_night = ?
            WHERE id = ?
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, newPrice);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating room price", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting room", e);
        }
    }
}
