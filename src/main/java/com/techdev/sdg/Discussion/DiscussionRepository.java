package com.techdev.sdg.Discussion;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    @EntityGraph(attributePaths  = {"project"})
    List<Discussion> findByProjectId(Long id);
}