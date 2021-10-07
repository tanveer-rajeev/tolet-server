package com.tolet.LivingService;

import com.tolet.BookingService.Booking;
import com.tolet.Exception.ResourceNotFoundException;
import com.tolet.UserService.User;
import com.tolet.UserService.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivingSpaceServiceImplementation implements LivingSpaceService {

    private final LivingSpaceRepository livingSpaceRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public LivingSpaceServiceImplementation(LivingSpaceRepository livingSpaceRepository,
                                            ModelMapper modelMapper, UserRepository userRepository) {
        this.livingSpaceRepository = livingSpaceRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<LivingSpace> getAllSpaces() {
        return livingSpaceRepository.findAll();
    }

    @Override
    public LivingSpace createSpace(LivingSpace livingSpace, Integer userId) {
        User user = userRepository.findById(userId).stream()
                .filter(user1 -> user1.getId().equals(userId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found"));
        livingSpace.setOwner(user);
        livingSpaceRepository.save(livingSpace);
        return livingSpace;
    }

    public LivingSpace getSpaceById(Integer spaceId) {
        return livingSpaceRepository
                .findById(spaceId)
                .stream()
                .filter(booking -> booking
                        .getId()
                        .equals(spaceId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Space id not found: " + spaceId));
    }


    @Override
    public List<Image> getImages(Integer spaceId) {
        return getSpaceById(spaceId).getImageURL_list();
    }

    @Override
    public ResponseEntity<HttpStatus> deleteSpace(Integer userId) {
        LivingSpace livingSpace = getSpaceById(userId);
        livingSpaceRepository.delete(livingSpace);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @Override
    public List<String> getAllImageURL(Integer spaceId) {
        LivingSpace livingSpace = getSpaceById(spaceId);
        return livingSpace.getImageURL_list()
                .stream()
                .map(Image::getImageURL)
                .collect(Collectors.toList());

    }

    @Override
    public List<Booking> getAllBookingRequest(Integer spaceId) {
        return getSpaceById(spaceId).getBookings();
    }

    @Override
    public LivingSpace updateLivingSpace(LivingSpaceRequest livingSpaceRequest, Integer spaceId, Integer userId) {
        LivingSpace livingSpace = modelMapper.map(livingSpaceRequest,LivingSpace.class);

        User user = userRepository.findById(userId).stream()
                .filter(user1 -> user1.getId().equals(userId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found"));
        livingSpace.setOwner(user);
        return livingSpaceRepository.save(livingSpace);
    }


}
