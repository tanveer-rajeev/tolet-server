package com.tolet.service;

import com.tolet.model.Image;
import com.tolet.model.Space;

import java.util.Set;

public interface SpaceService {
    void createSpace(Space space, Integer userId);
    Image addImage(Image image, Integer id);
    Set<Image> getImages(Integer id);
}
