package myApp.controller;

import myApp.model.EntityProject;
import myApp.model.EntityProjectMembers;
import myApp.model.EntityUser;
import myApp.service.ProjectService;
import myApp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    ProjectController(ProjectService projectService, UserService userService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @PostMapping("/hx/v1/projects/new")
    public String createProject(@RequestParam("name") String name,
                                @RequestParam("description") String description,
                                Model model,
                                Authentication authentication) {
        String username = authentication.getName();
        EntityUser currentUser = userService.findByUsername(username);

        EntityProject project = new EntityProject();
        project.setName(name);
        project.setDescription(description);
        project.setCreatedAt(LocalDateTime.now());
        projectService.saveProject(project);

        projectService.addMember(project.getId(), currentUser.getId());

        model.addAttribute("project", project);
        return "fragments/projects :: project-item";
    }

    @GetMapping("/hx/v1/projects/info")
    public String getProjectInfo(@RequestParam Long projectId, Model model) {
        EntityProject project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        return "fragments/projects :: project-info-content";
    }

    @PutMapping("/hx/v1/projects/update")
    public String updateProject(@RequestParam("projectId") Long projectId, @RequestParam("name") String name, @RequestParam("description") String description, Model model) {
        EntityProject project = projectService.getProjectById(projectId);
        if(project != null) {
            project.setName(name);
            project.setDescription(description);
            project.setUpdatedAt(LocalDateTime.now());
            projectService.saveProject(project);
        }

        model.addAttribute("projects", projectService.getProjects());
        model.addAttribute("selectedProject", projectService.getProjectById(projectId));

        return "fragments/projects :: change-block-on-update";
    }

    @DeleteMapping("hx/v1/projects/delete")
    @ResponseBody
    public String deleteProject(@RequestParam("projectId") Long projectId, Model model) {
        projectService.deleteProject(projectId);
        return "";
    }

    @GetMapping("/hx/v1/projects/members")
    public String getProjectMembers(@RequestParam("projectId") Long projectId, Model model) {
        EntityProject project = projectService.getProjectById(projectId);
        List<EntityProjectMembers> members = projectService.getProjectMembers(projectId);
        model.addAttribute("project", project);
        model.addAttribute("members", members);
        return "fragments/projects :: members-list-content";
    }

    @PostMapping("/hx/v1/projects/join")
    public String joinProject(@RequestParam("inviteCode") String inviteCode,
                              Model model,
                              Authentication authentication) {
        String username = authentication.getName();
        EntityUser currentUser = userService.findByUsername(username);

        EntityProject project = projectService.getProjectByInviteCode(inviteCode.trim().toUpperCase());
        if (project == null) {
            model.addAttribute("projects", projectService.getProjectsByUserId(currentUser.getId()));
            return "fragments/projects :: projects-list";
        }

        if (!projectService.isUserMemberOfProject(project.getId(), currentUser.getId())) {
            projectService.addMember(project.getId(), currentUser.getId());
        }

        List<EntityProject> userProjects = projectService.getProjectsByUserId(currentUser.getId());
        model.addAttribute("projects", userProjects);
        return "fragments/projects :: projects-list";
    }
}
