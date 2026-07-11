package myApp.service;

import jakarta.transaction.Transactional;
import myApp.model.EntityProject;
import myApp.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<EntityProject> getProjects() {
        return projectRepository.findAll();
    }

    public EntityProject getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void saveProject(EntityProject project) {
        projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
