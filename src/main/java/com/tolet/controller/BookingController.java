package com.tolet.controller;
import com.tolet.model.Booking;
import com.tolet.repository.BookingRepository;
import com.tolet.repository.RoomRepository;
import com.tolet.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/{spaceId}")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking , @PathVariable Integer spaceId) throws
            ParseException {
        return bookingService.makeBooking(booking,spaceId);
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable Integer bookingId){
        bookingRepository.deleteById(bookingId);
    }

}
