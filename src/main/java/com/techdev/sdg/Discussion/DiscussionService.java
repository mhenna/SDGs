package com.techdev.sdg.Discussion;


import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Project.ProjectRepository;
import com.techdev.sdg.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Repeatable;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    public Discussion save(Map<String, Object> body) throws Exception {
        Discussion discussion = new Discussion(
                Objects.toString(body.get(Discussion.QUESTION), null)
        );
        Optional<Project> project = projectRepository.findById(Long.parseLong(Objects.toString(body.get(Discussion.PROJECT))));
        if(project.isEmpty()){
            throw new Exception ("Project with specified id does not exist");
        }
        else {
            discussion.setProject(project.get());
            repository.save(discussion);
            return discussion;
        }
    }


}
