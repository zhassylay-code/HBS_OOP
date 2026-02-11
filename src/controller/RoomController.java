package controller;

import service.RoomService;

import java.math.BigDecimal;

public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void addRoom(String type, BigDecimal price) {
        roomService.addRoom(type, price);
        System.out.println("Room added successfully.");
    }

    public void updateRoomPrice(Long roomId, BigDecimal newPrice) {
        roomService.updateRoomPrice(roomId, newPrice);
        System.out.println("Room price updated successfully.");
    }

    public void deleteRoom(Long roomId) {
        roomService.deleteRoom(roomId);
        System.out.println("Room deleted successfully.");
    }
}
