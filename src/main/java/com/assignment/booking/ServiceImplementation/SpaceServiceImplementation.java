package com.assignment.booking.ServiceImplementation;

import com.assignment.booking.Exception.ResourceNotFoundException;
import com.assignment.booking.model.Image;
import com.assignment.booking.model.Space;
import com.assignment.booking.model.User;
import com.assignment.booking.repository.ImageRepository;
import com.assignment.booking.repository.SpaceRepository;
import com.assignment.booking.repository.UserRepository;
import com.assignment.booking.service.SpaceService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpaceServiceImplementation implements SpaceService {

    private final SpaceRepository spaceRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public SpaceServiceImplementation(SpaceRepository spaceRepository, ImageRepository imageRepository, UserRepository userRepository) {
        this.spaceRepository = spaceRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createSpace(Space space, Integer userId) {
        User user = userRepository.findById(userId).stream()
                .filter(user1 -> user1.getId().equals(userId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found"));
        space.setOwner(user);
        spaceRepository.save(space);
    }

    public Space getSpaceById(Integer id) {
        return spaceRepository
                .findById(id)
                .stream()
                .filter(booking -> booking
                        .getId()
                        .equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));
    }

    @Override
    public Image addImage(Image image, Integer id) {
        Space space = getSpaceById(id);
        image.setSpace(space);
        imageRepository.save(image);
        return image;
    }

    @Override
    public Set<Image> getImages(Integer id) {
        Space space = getSpaceById(id);
        return space.getImageURL_list();
    }
}
