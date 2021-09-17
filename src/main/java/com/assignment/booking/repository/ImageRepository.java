package com.assignment.booking.repository;

import com.assignment.booking.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
