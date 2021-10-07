package com.tolet.LivingService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivingSpaceRepository extends JpaRepository<LivingSpace, Integer> {
    List<LivingSpace> findByAreaOrderBySquareFeet(String area);

    List<LivingSpace> findByDistrictOrderByArea(String district);

    @Query("select s from LivingSpace s where s.monthlyRent = ?1 or s.advanceRent=?1")
    List<LivingSpace> findByMonthlyRentOrAdvancedRent(Integer monthlyRent);

    @Query("select s from LivingSpace s where s.monthlyRent between  :rent1 and :rent2")
    List<LivingSpace> findByMonthlyRentBetweenRange(@Param("rent1") Integer rent1, @Param("rent2") Integer rent2);

}
