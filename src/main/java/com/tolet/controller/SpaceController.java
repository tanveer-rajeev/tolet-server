package com.tolet.controller;
import com.tolet.Exception.ResourceNotFoundException;
import com.tolet.model.Image;
import com.tolet.model.Space;
import com.tolet.repository.ImageRepository;
import com.tolet.repository.SpaceRepository;
import com.tolet.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/spaces")
@CrossOrigin
public class SpaceController {
    private final SpaceService spaceService;
    private final ImageRepository imageRepository;
    private final SpaceRepository spaceRepository;
    @Autowired
    public SpaceController(SpaceService spaceService, ImageRepository imageRepository, SpaceRepository spaceRepository) {
        this.spaceService = spaceService;
        this.imageRepository = imageRepository;
        this.spaceRepository = spaceRepository;
    }

    @PostMapping("userId/{userId}/spaceTypeId/{spaceTypeId}")
    public ResponseEntity<Space> createSpace(@RequestBody Space space, @PathVariable Integer userId, @PathVariable Integer spaceTypeId){
       return  spaceService.createSpace(space,userId,spaceTypeId);
    }

    @DeleteMapping("spaceId/{userId}")
    public ResponseEntity<?> deleteSpace(@PathVariable Integer userId){
       return spaceService.deleteSpace(userId);
    }

    @GetMapping
    public List<Space> getAllSpaces(){
        return spaceService.getAllSpaces();
    }

    @PostMapping("/setImage/{id}")
    public Image setImage(@RequestBody Image image, @PathVariable Integer id){
        spaceService.addImage(image,id);
        return image;
    }

    @GetMapping("/getAllImageURL/{id}")
    public List<String> getImageURL(@PathVariable Integer id){
        return spaceService.getAllImageURL(id);
    }

    @GetMapping("/getAllImage/{id}")
    public List<Image> getImage(@PathVariable Integer id){
        Space space = spaceRepository.findById(id).stream()
                .filter(space1 -> space1.getId().equals(id))
                .findFirst().orElseThrow(()-> new ResourceNotFoundException("Space not found"));
        return imageRepository.findAll().stream().filter(image -> image.getSpace().equals(space))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/deleteImageById/{id}")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable Integer id){
        imageRepository.deleteById(id);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }
}
