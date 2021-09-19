package com.tolet.controller;

import com.tolet.model.Space;
import com.tolet.repository.SpaceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaces")
@CrossOrigin
public class SpaceFilterController {

    private final SpaceRepository spaceRepository;

    public SpaceFilterController(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    @GetMapping("district/{district}")
    public List<Space> filterSpaceByDistrict(@PathVariable String district ) {
        return spaceRepository.findByDistrictOrderByArea(district);

    }

    @GetMapping("area/{area}")
    public List<Space> filterSpaceByArea(@PathVariable String area ) {
        return spaceRepository.findByAreaOrderBySquareFeet(area);
    }

    @GetMapping("spaceType/{spaceType}")
    public List<Space> filterSpaceByTypeOfSpace(@PathVariable String spaceType ) {
        return spaceRepository.findBySpaceType(spaceType);
    }

    @GetMapping("rentSort/{rent}")
    public List<Space> filterByMonthlyRent(@PathVariable Integer rent){
        return spaceRepository.findByMonthlyRentOrAdvancedRent(rent);
    }
    @GetMapping("rentRange/{rent1}/{rent2}")
    public List<Space> findByRentRange(@PathVariable Integer rent1,@PathVariable Integer rent2){
        return spaceRepository.findByMonthlyRentBetweenRange(rent1,rent2);
    }
}
