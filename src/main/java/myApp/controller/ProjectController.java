package myApp.controller;

import myApp.model.EntityProject;
import myApp.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ProjectController {
    private final ProjectService projectService;

    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/hx/v1/projects/new")
    public String createProject(@RequestParam("name") String name, @RequestParam("description") String description, Model model) {
        EntityProject project = new EntityProject();
        project.setName(name);
        project.setDescription(description);
        project.setCreatedAt(LocalDateTime.now());
        projectService.saveProject(project);
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

        return "fragments/projects :: projects-list";
    }

    @DeleteMapping("hx/v1/projects/delete")
    public String deleteProject(@RequestParam("projectId") Long projectId, Model model) {
        projectService.deleteProject(projectId);
        return "";
    }
}
