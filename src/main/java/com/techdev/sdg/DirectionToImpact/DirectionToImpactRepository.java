package com.techdev.sdg.DirectionToImpact;


import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionToImpactRepository extends JpaRepository<DirectionToImpact, Long> {
}