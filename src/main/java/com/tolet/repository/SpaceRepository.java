package com.tolet.repository;

import com.tolet.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space,Integer> {
     List<Space> findByAreaOrderBySquareFeet(String area);
     List<Space> findByDistrictOrderByArea(String district);
     List<Space> findBySpaceType(String spaceType);

     @Query("select s from Space s where s.monthlyRent = ?1")
     List<Space> findByMonthlyRentOrAdvancedRent(Integer monthlyRent);


}
