package com.tolet.ServiceImplementation;

import com.tolet.Exception.ResourceNotFoundException;
import com.tolet.model.Space;
import com.tolet.model.SpaceType;
import com.tolet.repository.SpaceTypeRepository;
import com.tolet.service.SpaceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpaceTypeServiceImplementation implements SpaceTypeService {

    private final SpaceTypeRepository spaceTypeRepository;

    public SpaceTypeServiceImplementation(SpaceTypeRepository spaceTypeRepository) {
        this.spaceTypeRepository = spaceTypeRepository;
    }

    @Override
    public ResponseEntity<SpaceType> createSpaceType(SpaceType spaceType) {
        return ResponseEntity.ok().body(spaceTypeRepository.save(spaceType));
    }

    public SpaceType getSpaceTypeById(Integer spaceTypeId) {
        return spaceTypeRepository.findById(spaceTypeId)
                .stream()
                .filter(spaceType -> spaceType.getId().equals(spaceTypeId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Space type not found by given id"));
    }

    @Override
    public ResponseEntity<HttpStatus> deleteSpaceType(Integer id) {
        SpaceType spaceType = getSpaceTypeById(id);
        spaceTypeRepository.delete(spaceType);
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<SpaceType> updateSpaceType(SpaceType spaceType) {
        return null;
    }
}
