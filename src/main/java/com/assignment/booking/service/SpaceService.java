package com.assignment.booking.service;

import com.assignment.booking.model.Image;
import com.assignment.booking.model.Space;

import java.util.Set;

public interface SpaceService {
    void createSpace(Space space,Integer userId);
    Image addImage(Image image, Integer id);
    Set<Image> getImages(Integer id);
}
