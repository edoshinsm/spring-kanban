package myApp.service;

import jakarta.transaction.Transactional;
import myApp.model.EntityIssue;
import myApp.model.EntityProject;
import myApp.model.EntityProjectMembers;
import myApp.model.EntityUser;
import myApp.repository.ProjectMembersRepository;
import myApp.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMembersRepository projectMemberRepository;
    private final UserService userService;

    ProjectService(ProjectRepository projectRepository, ProjectMembersRepository projectMembersRepository, UserService userService) {
        this.projectMemberRepository = projectMembersRepository;
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public List<EntityProject> getProjects() {
        return projectRepository.findAll();
    }

    public boolean isUserMemberOfProject(Long projectId, Long userId) {
        return projectMemberRepository.existsByProjectIdAndUserId(projectId, userId);
    }

    public List<EntityProject> getProjectsByUserId(Long userId) {
        return projectMemberRepository.findByUserId(userId).stream()
                .map(EntityProjectMembers::getProject)
                .collect(Collectors.toList());
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

    @Transactional
    public void addMember(Long projectId, Long userId) {
        if (!projectMemberRepository.existsByProjectIdAndUserId(projectId, userId)) {
            EntityProject project = getProjectById(projectId);
            EntityUser user = userService.findById(userId);
            EntityProjectMembers projectMembers = new EntityProjectMembers();
            projectMembers.setProject(project);
            projectMembers.setUser(user);
            projectMembers.setRole("MEMBER");
            projectMemberRepository.save(projectMembers);
        }
    }

}
