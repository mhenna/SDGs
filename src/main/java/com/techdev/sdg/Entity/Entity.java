package com.techdev.sdg.Entity;

import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.WorkLocation.WorkLocation;
import com.techdev.sdg.intendedSDG.IntendedSDG;

import java.util.Map;
import java.util.Set;

public abstract class Entity {
    public void setName(String name) { };

    public abstract void setEmail(String email);

    public abstract void setPassword(String password);

    public abstract void setIsApproved(Boolean isApproved);

    public abstract void setProjects(Set<Project> project);

    public abstract void setWorkLocations(Set<WorkLocation> workLocations);

    public abstract void setResources(Set<Resource> resources);

    public abstract void setDirectionToImpact(Set<DirectionToImpact> directionToImpact);

    public abstract void setIntendedSDGs(Set<IntendedSDG> intendedSDGs);

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getPassword();

    public abstract Boolean getIsApproved();

    public abstract Set<Project> getProjects();

    public abstract Set<WorkLocation> getWorkLocations();

    public abstract Set<Resource> getResources();

    public abstract Set<DirectionToImpact> getDirectionToImpact();

    public abstract Set<IntendedSDG> getIntendedSDGs();

    public abstract Map<String, Object> toMap();

    public abstract String toString();

    public abstract Class getType();
}
