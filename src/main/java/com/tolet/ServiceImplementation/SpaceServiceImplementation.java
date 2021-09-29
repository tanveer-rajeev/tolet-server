package com.tolet.ServiceImplementation;

import com.tolet.Exception.ResourceNotFoundException;
import com.tolet.model.Image;
import com.tolet.model.Space;
import com.tolet.model.SpaceType;
import com.tolet.model.User;
import com.tolet.repository.ImageRepository;
import com.tolet.repository.SpaceRepository;
import com.tolet.repository.SpaceTypeRepository;
import com.tolet.repository.UserRepository;
import com.tolet.service.SpaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceServiceImplementation implements SpaceService {

    private final SpaceRepository spaceRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final SpaceTypeRepository spaceTypeRepository;

    public SpaceServiceImplementation(SpaceRepository spaceRepository, ImageRepository imageRepository, UserRepository userRepository, SpaceTypeRepository spaceTypeRepository) {
        this.spaceRepository = spaceRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.spaceTypeRepository = spaceTypeRepository;
    }

    @Override
    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    @Override
    public ResponseEntity<Space> createSpace(Space space, Integer userId, Integer spaceTpeId) {
        SpaceType spaceType = spaceTypeRepository.findById(spaceTpeId)
                .stream().filter(spaceType1 -> spaceType1.getId().equals(spaceTpeId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Space type not found"));
        User user = userRepository.findById(userId).stream()
                .filter(user1 -> user1.getId().equals(userId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found"));
        space.setOwner(user);
        space.setSpaceType(spaceType);
        spaceRepository.save(space);
        return ResponseEntity.ok().body(space);
    }

    public  Space getSpaceById(Integer id) {
        return spaceRepository
                .findById(id)
                .stream()
                .filter(booking -> booking
                        .getId()
                        .equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Space id not found: " + id));
    }

    @Override
    public Image addImage(Image image, Integer id) {
        Space space = getSpaceById(id);
        image.setSpace(space);
        imageRepository.save(image);
        return image;
    }

    @Override
    public List<Image> getImages(Integer id) {
        Space space = getSpaceById(id);
        System.out.println(space.getImageURL_list());
        return space.getImageURL_list();
    }

    @Override
    public ResponseEntity<HttpStatus> deleteSpace(Integer userId) {
        Space space = getSpaceById(userId);
        spaceRepository.delete(space);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @Override
    public List<String> getAllImageURL(Integer spaceId) {
        Space space = getSpaceById(spaceId);
        return imageRepository
                .findAll().stream()
                .filter(image -> image.getSpace().equals(space))
                .map(Image::getImageURL)
                .collect(Collectors.toList());
    }

}
