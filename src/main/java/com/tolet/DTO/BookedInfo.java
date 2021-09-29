package com.tolet.DTO;

import lombok.Data;
import lombok.ToString;
import java.util.List;


@Data
@ToString
public class BookedInfo {

    private List<String> availableBookingDate;

}
