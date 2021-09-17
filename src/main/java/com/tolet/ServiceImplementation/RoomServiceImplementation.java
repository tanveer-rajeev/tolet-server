package com.tolet.ServiceImplementation;

import com.tolet.model.Room;
import com.tolet.repository.RoomRepository;
import com.tolet.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImplementation implements RoomService {

    final private RoomRepository roomRepository;

    public RoomServiceImplementation(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Integer roomId) {
        return roomRepository.findById(roomId)
                .stream()
                .filter(room -> room.getId().equals(roomId))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    public Room findRoomByRoomName(String name){
        return roomRepository.findByRoomName(name);
    }
}
