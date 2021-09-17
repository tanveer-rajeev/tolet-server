package com.tolet.controller;
import com.tolet.model.Room;
import com.tolet.repository.RoomRepository;
import com.tolet.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;
    private final RoomService roomService;
    @Autowired
    public RoomController(RoomRepository roomRepository, RoomService roomService) {
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    @GetMapping("/{roomName}")
    public Room getRoom(@PathVariable String roomName){
        return roomRepository.findByRoomName(roomName);
    }

    @GetMapping
    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room){
        return roomService.addRoom(room);
    }
}
