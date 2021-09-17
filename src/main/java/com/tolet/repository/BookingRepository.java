package com.tolet.repository;

import com.tolet.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findByBookingDate(String bookingDate);
}
