package repository;

import entity.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestRepositoryImpl implements GuestRepository {

    private final Connection conn;

    public GuestRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Guest findById(Long id) {
        String sql = """
            SELECT id, full_name, document
            FROM guests
            WHERE id = ?
            """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Guest guest = new Guest();
            guest.id = rs.getLong("id");
            guest.fullName = rs.getString("full_name");
            guest.document = rs.getString("document");

            return guest;

        } catch (SQLException e) {
            throw new RuntimeException("Error finding guest by id", e);
        }
    }
    @Override
    public Guest findByDocument(String document) {
        String sql = """
        SELECT id, full_name, document
        FROM guests
        WHERE document = ?
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, document);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            Guest guest = new Guest();
            guest.id = rs.getLong("id");
            guest.fullName = rs.getString("full_name");
            guest.document = rs.getString("document");

            return guest;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Guest save(Guest guest) {
        String sql = """
        INSERT INTO guests (full_name, document)
        VALUES (?, ?)
        RETURNING id
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, guest.fullName);
            ps.setString(2, guest.document);

            ResultSet rs = ps.executeQuery();
            rs.next();
            guest.id = rs.getLong("id");

            return guest;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
