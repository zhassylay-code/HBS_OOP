package service;

import auth.Session;
import entity.Role;
import entity.Room;
import repository.RoomRepository;

import java.math.BigDecimal;

public class RoomService {
    private final RoomRepository roomRepo;

    public RoomService(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    public void addRoom(String type, BigDecimal pricePerNight) {
        Session.require(Role.ADMIN);
        Room r = new Room();
        r.type = type;
        r.pricePerNight = pricePerNight;
        roomRepo.save(r);
    }

    public void updateRoomPrice(Long roomId, BigDecimal newPrice) {
        Session.require(Role.ADMIN);
        roomRepo.updatePrice(roomId, newPrice);
    }

    public void deleteRoom(Long roomId) {
        Session.require(Role.ADMIN);
        roomRepo.deleteById(roomId);
    }
}
