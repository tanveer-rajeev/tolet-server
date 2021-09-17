package com.tolet.DTO;

import com.tolet.response.RoomResponse;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@ToString
public class BookedInfo {

    private List<String> bookedPersonsList;
    private Set<RoomResponse> roomList;
    private boolean trigger = false;

}
