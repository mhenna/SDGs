package com.techdev.sdg.intendedSDG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntendedSDGRepository extends JpaRepository<IntendedSDG, Long> {
}