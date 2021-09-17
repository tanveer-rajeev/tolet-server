package com.assignment.booking.controller;
import com.assignment.booking.model.Image;
import com.assignment.booking.model.Space;
import com.assignment.booking.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    private final SpaceService spaceService;

    @Autowired
    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping("/{userId}")
    public void createSpace(@RequestBody Space space, @PathVariable Integer userId){
         spaceService.createSpace(space,userId);
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
