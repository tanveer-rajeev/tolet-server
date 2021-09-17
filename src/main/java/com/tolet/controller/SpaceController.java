package com.tolet.controller;
import com.tolet.model.Image;
import com.tolet.model.Space;
import com.tolet.service.SpaceService;
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
