package com.assignment.booking.repository;

import com.assignment.booking.model.Booking;
import com.assignment.booking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findByBookingDate(String bookingDate);
}
