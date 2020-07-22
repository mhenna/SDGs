package com.techdev.sdg.NGO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NGORepository extends JpaRepository<NGO, Long> {
    NGO findByName(String name);

    List<NGO> findByIsApproved(Boolean isApproved);
}
