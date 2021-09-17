package com.assignment.booking.service;

import com.assignment.booking.model.Room;

import java.util.List;

public interface RoomService {
    Room addRoom(Room room);
    Room getRoomById(Integer room);
    List<Room> getAllRoom();
}
