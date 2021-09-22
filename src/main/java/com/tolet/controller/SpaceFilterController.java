package com.tolet.controller;

import com.tolet.model.Space;
import com.tolet.model.SpaceType;
import com.tolet.repository.SpaceRepository;
import com.tolet.repository.SpaceTypeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/spaces")
@CrossOrigin
public class SpaceFilterController {

    private final SpaceRepository spaceRepository;
    private final SpaceTypeRepository spaceTypeRepository;

    public SpaceFilterController(SpaceRepository spaceRepository, SpaceTypeRepository spaceTypeRepository) {
        this.spaceRepository = spaceRepository;
        this.spaceTypeRepository = spaceTypeRepository;
    }

    @GetMapping("district/{district}")
    public List<Space> filterSpaceByDistrict(@PathVariable String district) {
        return spaceRepository.findByDistrictOrderByArea(district);

    }

    @GetMapping("/types")
    public List<String> getAllSpacesType() {
        return spaceTypeRepository.findAll()
                .stream()
                .map(SpaceType::getSpaceType)
                .collect(Collectors.toList());
    }

    @GetMapping("area/{area}")
    public List<Space> filterSpaceByArea(@PathVariable String area) {
        return spaceRepository.findByAreaOrderBySquareFeet(area);
    }

    @GetMapping("spaceType/{spaceTypeName}")
    public List<Space> filterSpaceByTypeOfSpace(@PathVariable String spaceTypeName) {
        SpaceType spaceType = spaceTypeRepository.findBySpaceType(spaceTypeName);
        return spaceType.getSpaceList();
    }

    @GetMapping("rentSort/{rent}")
    public List<Space> filterByMonthlyRent(@PathVariable Integer rent) {
        return spaceRepository.findByMonthlyRentOrAdvancedRent(rent);
    }

    @GetMapping("rentRange/{rent1}/{rent2}")
    public List<Space> findByRentRange(@PathVariable Integer rent1, @PathVariable Integer rent2) {
        return spaceRepository.findByMonthlyRentBetweenRange(rent1, rent2);
    }
}
