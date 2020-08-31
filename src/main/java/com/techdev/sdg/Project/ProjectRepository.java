package com.techdev.sdg.Project;

import com.techdev.sdg.Entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findProjectById(long id);
    List<Project> findByViewersContaining(Entity viewer);
    List<Project> findByOwner(Entity owner);
}
