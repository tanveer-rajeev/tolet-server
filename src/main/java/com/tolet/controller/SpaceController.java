package com.tolet.controller;
import com.tolet.model.Image;
import com.tolet.model.Space;
import com.tolet.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/spaces")
@CrossOrigin
public class SpaceController {
    private final SpaceService spaceService;

    @Autowired
    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping("userId/{userId}/spaceTypeId/{spaceTypeId}")
    public ResponseEntity<Space> createSpace(@RequestBody Space space, @PathVariable Integer userId, @PathVariable Integer spaceTypeId){
       return  spaceService.createSpace(space,userId,spaceTypeId);
    }

    @DeleteMapping("userId/{userId}")
    public ResponseEntity<?> deleteSpace(@PathVariable Integer userId){
       return spaceService.deleteSpace(userId);
    }

    @GetMapping
    public List<Space> getAllSpaces(){
        return spaceService.getAllSpaces();
    }

    @PostMapping("/setImage/{id}")
    public void setImage(@RequestBody Image image, @PathVariable Integer id){
        spaceService.addImage(image,id);
    }

    @GetMapping("/getImage/{id}")
    public Set<Image> getImage(@PathVariable Integer id){
        return spaceService.getImages(id);
    }
}
