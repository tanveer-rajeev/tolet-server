package com.tolet.BookingService;

import com.tolet.Exception.ResourceNotFoundException;
import com.tolet.LivingService.LivingSpace;
import com.tolet.LivingService.LivingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImplementation implements BookingService {

    private final BookingRepository bookingRepository;
    private final LivingSpaceRepository livingSpaceRepository;

    @Autowired
    public BookingServiceImplementation(BookingRepository bookingRepository, LivingSpaceRepository livingSpaceRepository) {
        this.bookingRepository = bookingRepository;
        this.livingSpaceRepository = livingSpaceRepository;
    }

    @Override
    public Booking getBookingById(Integer id) {
        return bookingRepository
                .findById(id)
                .stream()
                .filter(booking -> booking
                        .getId()
                        .equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
    }


    // TODO: Make Booking
    @Override
    public ResponseEntity<?> makeBooking(Booking bookingRequest, Integer spaceId) throws ParseException {

        // check booking date validation
        if (!checkValidationOfBookingDate(bookingRequest.getBookingDate())) {
            throw new ResourceNotFoundException("- Booking date not valid " + bookingRequest.getBookingDate());
        }

        LivingSpace requestedLivingSpace = livingSpaceRepository.findById(spaceId)
                .stream()
                .filter(space1 -> space1.getId().equals(spaceId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Space not found"));

        bookingRequest.setLivingSpace(requestedLivingSpace);
        // TODO: Booking service have to be generic
//        boolean isPerDayRentSpaceForBusiness = requestedLiving.getPerDayHourlyRent() > 0;
//
//        if (isPerDayRentSpaceForBusiness) {
//            List<Booking> bookedList = requestedLiving.getBookings();
//
//            if (isWorkingPlaceAvailable(bookingRequest, bookedList)) {
//                Booking bookingConfirmation = bookingRepository.save(bookingRequest);
//                return new ResponseEntity<>(bookingConfirmation, HttpStatus.ACCEPTED);
//            }
//            BookedInfo bookedInfo = getInformedForAvailableDate(requestedLiving, bookingRequest.getBookingDate());
//
//            throw new BookedNotificationHandler("Already booked name list", bookedInfo);
//        }

        return new ResponseEntity<>(bookingRepository.save(bookingRequest), HttpStatus.ACCEPTED);
    }

    //  check every working place into the room has  same bookingRequest date or not
    public boolean isWorkingPlaceAvailable(Booking bookingRequest, List<Booking> bookingList) {

        if (bookingList.size() == 0) return true;

        return bookingList.stream().noneMatch(booking -> booking
                .getBookingDate()
                .equals(bookingRequest.getBookingDate()));
    }

    //  User get informed available room with working place and also booked user name
    public BookedInfo getInformedForAvailableDate(LivingSpace requestedLivingSpace, String bookingDate) {

        // --------Try available room with open working place--------
        List<String> availableBookingDate = requestedLivingSpace.getBookings()
                .stream().map(Booking::getBookingDate)
                .filter(date -> !date.equals(bookingDate))
                .collect(Collectors.toList());

        BookedInfo bookedInfo = new BookedInfo();
        bookedInfo.setAvailableBookingDate(availableBookingDate);
        return bookedInfo;
    }

    private boolean checkValidationOfBookingDate(String requestBookingDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date requestDate = sdf.parse(requestBookingDate);
        Date currentDate = sdf.parse(String.valueOf(LocalDate.now()));

        return requestDate.getTime() >= currentDate.getTime();
    }


}
