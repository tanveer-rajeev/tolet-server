package com.tolet.service;

import com.tolet.model.Image;
import com.tolet.model.Space;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface SpaceService {
    List<Space> getAllSpaces();
    ResponseEntity<Space> createSpace(Space space, Integer userId,Integer spaceTypeId);
    Image addImage(Image image, Integer id);
    Set<Image> getImages(Integer id);
    ResponseEntity<HttpStatus> deleteSpace(Integer userId);

}
