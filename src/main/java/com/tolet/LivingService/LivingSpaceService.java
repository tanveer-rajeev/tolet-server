package com.tolet.LivingService;
import com.tolet.BookingService.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LivingSpaceService {
    List<LivingSpace> getAllSpaces();

    LivingSpace createSpace(LivingSpace livingSpace, Integer userId);

    List<Image> getImages(Integer id);

    ResponseEntity<HttpStatus> deleteSpace(Integer userId);

    List<String> getAllImageURL(Integer spaceId);

    List<Booking> getAllBookingRequest(Integer spaceId);

    LivingSpace updateLivingSpace(LivingSpaceRequest livingSpaceRequest,Integer spaceId,Integer userId);
}
