package com.tolet.service;
import com.tolet.model.SpaceType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface SpaceTypeService {
    ResponseEntity<SpaceType> createSpaceType(SpaceType spaceType);
    ResponseEntity<HttpStatus> deleteSpaceType(Integer id);
    ResponseEntity<SpaceType> updateSpaceType(SpaceType spaceType);
}
