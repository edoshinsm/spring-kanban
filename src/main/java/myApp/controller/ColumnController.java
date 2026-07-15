package myApp.controller;

import myApp.model.EntityColumn;
import myApp.service.ColumnService;
import myApp.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ColumnController {
    private final ColumnService columnService;
    private final ProjectService projectService;

    ColumnController(ColumnService columnService, ProjectService projectService) {
        this.columnService = columnService;
        this.projectService = projectService;
    }

    @PostMapping("/hx/v1/columns/new")
    public String createColumn(@RequestParam("name") String name,
                               @RequestParam("project_id") Long projectId,
                               Model model) {
        EntityColumn column = new EntityColumn();
        column.setName(name);
        column.setProject(projectService.getProjectById(projectId));
        column.setCreatedAt(LocalDateTime.now());
        columnService.saveColumn(column);
        model.addAttribute("column", column);
        model.addAttribute("columns", columnService.findByProjectId(projectId));
        return "fragments/columns :: change-block-on-create";
    }

    @GetMapping("/hx/v1/columns/info")
    public String getColumnInfo(@RequestParam("columnId") Long columnId, Model model) {
        EntityColumn column = columnService.getColumnById(columnId);
        model.addAttribute("column", column);
        return "fragments/columns :: column-info-content";
    }

    @PutMapping("/hx/v1/columns/update")
    public String updateColumn(@RequestParam("project_id") Long projectId,
                               @RequestParam("columnId") Long columnId,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description,
                               Model model) {
        EntityColumn column = columnService.getColumnById(columnId);
        if (column != null) {
            column.setName(name);
            column.setDescription(description);
            column.setUpdatedAt(LocalDateTime.now());
            columnService.saveColumn(column);
        }
        model.addAttribute("columns", columnService.findByProjectId(projectId));
        return "fragments/columns :: columns-list";
    }

    @DeleteMapping("/hx/v1/column/delete")
    public String deleteColumn(@RequestParam("columnId") Long columnId, Model model) {
        EntityColumn column = columnService.getColumnById(columnId);
        if (column != null) {
            Long projectId = column.getProject().getId();
            columnService.deleteColumnById(columnId);
            model.addAttribute("columns", columnService.findByProjectId(projectId));
        }
        return "fragments/columns :: columns-list";
    }
}