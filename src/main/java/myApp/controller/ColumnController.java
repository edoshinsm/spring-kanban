package myApp.controller;

import myApp.model.EntityColumn;
import myApp.service.ColumnService;
import myApp.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ColumnController {
    private final ColumnService columnService;
    private final ProjectService projectService;

    ColumnController(ColumnService columnService, ProjectService projectService) {
        this.columnService = columnService;
        this.projectService = projectService;
    }

    @PostMapping("/hx/v1/boards")
    public String createColumn(@RequestParam("name") String name, @RequestParam("project_id") Long projectId, Model model) {
        EntityColumn column = new EntityColumn();
        column.setName(name);
        column.setProject(projectService.getProjectById(projectId));
        columnService.saveColumn(column);
        model.addAttribute("board", column);
        return "fragments/sidebar :: board-item";
    }
}
