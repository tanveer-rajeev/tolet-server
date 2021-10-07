package com.tolet.BookingService;

import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface BookingService {
    Booking getBookingById(Integer id);

    ResponseEntity<?> makeBooking(Booking booking, Integer space) throws ParseException;

}
