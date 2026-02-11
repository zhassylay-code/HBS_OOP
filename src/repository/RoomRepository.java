package repository;

import entity.Room;
import java.math.BigDecimal;
import java.util.List;

public interface RoomRepository {
    Room findById(Long id);
    List<Room> findAll();

    void save(Room room);
    void updatePrice(Long id, BigDecimal newPrice);
    void deleteById(Long id);
}
