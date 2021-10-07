package com.tolet.CommercialService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialSpaceRepository extends JpaRepository<CommercialSpace,Integer> {
}
