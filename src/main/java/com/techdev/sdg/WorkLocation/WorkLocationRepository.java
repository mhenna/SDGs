package com.techdev.sdg.WorkLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkLocationRepository extends JpaRepository<WorkLocation, Long> {
}
