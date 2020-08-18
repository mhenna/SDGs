package com.techdev.sdg.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Long> {

    Entity findByName (String name);

    List<Entity> findByIsApproved(Boolean isApproved);

    List<Entity> findByType(String type);
}
