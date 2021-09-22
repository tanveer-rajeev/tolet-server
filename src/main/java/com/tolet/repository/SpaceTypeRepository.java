package com.tolet.repository;

import com.tolet.model.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceTypeRepository extends JpaRepository<SpaceType, Integer> {
    SpaceType findBySpaceType(String spaceType);
}
