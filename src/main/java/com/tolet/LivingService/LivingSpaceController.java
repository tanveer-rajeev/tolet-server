package com.tolet.LivingService;
import com.tolet.BookingService.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/living")
@CrossOrigin
public class LivingSpaceController {
    private final LivingSpaceService livingSpaceService;

    @Autowired
    public LivingSpaceController(LivingSpaceService livingSpaceService) {
        this.livingSpaceService = livingSpaceService;

    }

    @PostMapping("userId/{userId}")
    public LivingSpace createSpace(@RequestBody LivingSpace livingSpace, @PathVariable Integer userId) {
        return livingSpaceService.createSpace(livingSpace, userId);
    }

    @GetMapping("bookings/{spaceId}")
    public List<Booking> getAllBookingRequest(@PathVariable Integer spaceId){
        return livingSpaceService.getAllBookingRequest(spaceId);
    }

    @DeleteMapping("spaceId/{spaceId}")
    public ResponseEntity<?> deleteSpace(@PathVariable Integer spaceId) {
        return livingSpaceService.deleteSpace(spaceId);
    }

    @GetMapping
    public List<LivingSpace> getAllSpaces() {
        return livingSpaceService.getAllSpaces();
    }


    @PutMapping("spaceId/{spaceId}/userId/{userId}")
    public LivingSpace updateSpace(@RequestBody LivingSpaceRequest livingSpaceRequest,@PathVariable Integer spaceId, @PathVariable Integer userId){
        return livingSpaceService.updateLivingSpace(livingSpaceRequest,spaceId,userId);
    }

    @GetMapping("/getAllImageURL/{spaceId}")
    public List<String> getImageURL(@PathVariable Integer spaceId) {
        return livingSpaceService.getAllImageURL(spaceId);
    }

    @GetMapping("/getAllImage/{spaceId}")
    public List<Image> getImage(@PathVariable Integer spaceId) {
       return livingSpaceService.getImages(spaceId);

    }

}
