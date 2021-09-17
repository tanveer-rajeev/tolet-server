package com.tolet.repository;

import com.tolet.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space,Integer> {
     List<Space> findByArea(String area);
     List<Space> findByDistrict(String district);
     List<Space> findBySpaceType(String spaceType);
}
