package myApp.controller;

import myApp.model.EntityProject;
import myApp.service.ColumnService;
import myApp.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {

    private final ProjectService projectService;
    private final ColumnService columnService;

    BaseController(ProjectService projectService, ColumnService columnService) {
        this.projectService = projectService;
        this.columnService = columnService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String base(Model model) {
        model.addAttribute("projects", projectService.getProjects());
        return "kanban";
    }

    @GetMapping("/project")
    public String selectProject(@RequestParam("id") Long id, Model model) {
        EntityProject project = projectService.getProjectById(id);
        model.addAttribute("projects", projectService.getProjects());
        model.addAttribute("selectedProject", projectService.getProjectById(id));
        model.addAttribute("boards", columnService.findByProjectId(id));
        return "kanban";
    }
}
