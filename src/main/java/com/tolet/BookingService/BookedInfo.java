package com.tolet.BookingService;

import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class BookedInfo {

    private List<String> availableBookingDate;

}
