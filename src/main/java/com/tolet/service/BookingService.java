package com.tolet.service;

import com.tolet.DTO.BookingDTO;
import com.tolet.model.Booking;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface BookingService {
    Booking getBookingById(Integer id);
    ResponseEntity<?> makeBooking(BookingDTO booking, String roomName) throws ParseException;
    int getCapacityFreeWorkingPlace(String requestedBookingDate) throws ParseException;
}
