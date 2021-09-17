package com.assignment.booking.repository;

import com.assignment.booking.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space,Integer> {

}
