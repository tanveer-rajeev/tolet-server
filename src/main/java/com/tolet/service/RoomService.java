package com.tolet.service;

import com.tolet.model.Room;

import java.util.List;

public interface RoomService {
    Room addRoom(Room room);
    Room getRoomById(Integer room);
    List<Room> getAllRoom();
}
