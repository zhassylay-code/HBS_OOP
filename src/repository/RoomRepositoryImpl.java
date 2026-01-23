package repository;

import entity.Room;

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
}
