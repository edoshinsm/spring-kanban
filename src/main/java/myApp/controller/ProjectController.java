package myApp.controller;

import myApp.model.EntityProject;
import myApp.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {
    private final ProjectService projectService;

    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/hx/v1/projects")
    public String createProject(@RequestParam("name") String name, Model model) {
        EntityProject project = new EntityProject();
        project.setName(name);
        projectService.saveProject(project);
        model.addAttribute("project", project);
        return "fragments/items :: project-item";
    }
}
