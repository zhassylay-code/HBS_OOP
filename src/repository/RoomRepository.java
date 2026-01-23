package repository;

import entity.Room;
import java.util.List;

public interface RoomRepository {
    Room findById(Long id);
    List<Room> findAll();
}
