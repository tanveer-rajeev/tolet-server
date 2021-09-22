package com.tolet.controller;
import com.tolet.model.SpaceType;
import com.tolet.service.SpaceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaceType")
public class SpaceTypeController {
   private final SpaceTypeService spaceTypeService;

    public SpaceTypeController(SpaceTypeService spaceTypeService) {
        this.spaceTypeService = spaceTypeService;
    }

    @PostMapping
    public ResponseEntity<SpaceType> createSpaceType(@RequestBody SpaceType spaceType){
        return spaceTypeService.createSpaceType(spaceType);
    }

    @DeleteMapping("/spaceTypeId/{id}")
    public ResponseEntity<HttpStatus> deleteSpaceType(@PathVariable Integer id){
        return spaceTypeService.deleteSpaceType(id);
    }
}
