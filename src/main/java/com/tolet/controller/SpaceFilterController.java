package com.tolet.controller;

import com.tolet.LivingService.LivingSpace;
import com.tolet.LivingService.LivingSpaceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/spaces")
@CrossOrigin
public class SpaceFilterController {

    private final LivingSpaceRepository livingSpaceRepository;


    public SpaceFilterController(LivingSpaceRepository livingSpaceRepository) {
        this.livingSpaceRepository = livingSpaceRepository;

    }

    @GetMapping("district/{district}")
    public List<LivingSpace> filterSpaceByDistrict(@PathVariable String district) {
        return livingSpaceRepository.findByDistrictOrderByArea(district);

    }

    @GetMapping("area/{area}")
    public List<LivingSpace> filterSpaceByArea(@PathVariable String area) {
        return livingSpaceRepository.findByAreaOrderBySquareFeet(area);
    }


    @GetMapping("rentSort/{rent}")
    public List<LivingSpace> filterByMonthlyRent(@PathVariable Integer rent) {
        return livingSpaceRepository.findByMonthlyRentOrAdvancedRent(rent);
    }

    @GetMapping("rentRange/{rent1}/{rent2}")
    public List<LivingSpace> findByRentRange(@PathVariable Integer rent1, @PathVariable Integer rent2) {
        return livingSpaceRepository.findByMonthlyRentBetweenRange(rent1, rent2);
    }
}
