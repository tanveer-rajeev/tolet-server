package com.tolet.ServiceImplementation;

import com.tolet.DTO.BookedInfo;
import com.tolet.Exception.BookedNotificationHandler;
import com.tolet.Exception.ResourceNotFoundException;
import com.tolet.model.Booking;
import com.tolet.model.Room;
import com.tolet.model.Space;
import com.tolet.repository.BookingRepository;
import com.tolet.repository.RoomRepository;
import com.tolet.repository.SpaceRepository;
import com.tolet.repository.UserRepository;
import com.tolet.response.RoomResponse;
import com.tolet.service.BookingService;
import org.modelmapper.ModelMapper;
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
    private final SpaceRepository spaceRepository;

    @Autowired
    public BookingServiceImplementation(BookingRepository bookingRepository, SpaceRepository spaceRepository) {
        this.bookingRepository = bookingRepository;
        this.spaceRepository = spaceRepository;
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

        Space requestedSpace = spaceRepository.findById(spaceId)
                .stream()
                .filter(space1 -> space1.getId().equals(spaceId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Space not found"));

        bookingRequest.setSpace(requestedSpace);
        boolean isPerDayRentSpaceForBusiness = requestedSpace.getPerDayHourlyRent() > 0;

        if (isPerDayRentSpaceForBusiness) {
            List<Booking> bookedList = requestedSpace.getBookings();

            if (isWorkingPlaceAvailable(bookingRequest, bookedList)) {
                Booking bookingConfirmation = bookingRepository.save(bookingRequest);
                return new ResponseEntity<>(bookingConfirmation, HttpStatus.ACCEPTED);
            }
            BookedInfo bookedInfo = getInformedForAvailableDate(requestedSpace, bookingRequest.getBookingDate());

            throw new BookedNotificationHandler("Already booked name list", bookedInfo);
        }

        return new ResponseEntity<>(bookingRepository.save(bookingRequest), HttpStatus.ACCEPTED);
    }

    // TODO: check every working place into the room has  same bookingRequest date or not
    public boolean isWorkingPlaceAvailable(Booking bookingRequest, List<Booking> bookingList) {

        if (bookingList.size() == 0) return true;

        return bookingList.stream().noneMatch(booking -> booking
                .getBookingDate()
                .equals(bookingRequest.getBookingDate()));
    }

    // TODO: User get informed available room with working place and also booked user name
    public BookedInfo getInformedForAvailableDate(Space requestedSpace, String bookingDate) {

        // --------Try available room with open working place--------
        List<String> availableBookingDate = requestedSpace.getBookings()
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
