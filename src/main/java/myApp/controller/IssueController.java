package myApp.controller;

import myApp.model.EntityColumn;
import myApp.model.EntityIssue;
import myApp.service.ColumnService;
import myApp.service.IssueService;
import myApp.service.ProjectService;
import myApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class IssueController {
    private final IssueService issueService;
    private final ColumnService columnService;
    private final ProjectService projectService;
    private final UserService userService;

    public IssueController(IssueService issueService, ColumnService columnService, ProjectService projectService, UserService userService) {
        this.issueService = issueService;
        this.columnService = columnService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping("/hx/v1/issues/new")
    public String createIssue(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("column_id") Long columnId,
                              @RequestParam("project_id") Long projectId,
                              Model model) {
        EntityColumn column = columnService.getColumnById(columnId);
        EntityIssue issue = new EntityIssue();
        issue.setName(name);
        issue.setDescription(description);
        issue.setColumn(column);
        issue.setStatus("открыта");
        issue.setPosition(issueService.findByColumnId(columnId).size() + 1L);
        issue.setCreatedAt(LocalDateTime.now());
        issue.setCreator(userService.findById(1L));
        issueService.saveIssue(issue);

        model.addAttribute("issue", issue);
        model.addAttribute("allIssues", issueService.findByProjectId(projectId));
        return "fragments/issues :: change-block-on-create";
    }

    @GetMapping("/hx/v1/issues/info")
    public String getIssueInfo(@RequestParam("issueId") Long issueId, Model model) {
        EntityIssue issue = issueService.getIssueById(issueId);
        if (issue == null) {
            return "fragments/issues :: issue-info-content";
        }
        model.addAttribute("issue", issue);
        return "fragments/issues :: issue-info-content";
    }

    @PutMapping("/hx/v1/issues/update")
    public String updateIssue(@RequestParam("issueId") Long issueId,
                              @RequestParam("column_id") Long columnId,
                              @RequestParam("project_id") Long projectId,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("status") String status,
                              @RequestParam(value = "dueDate", required = false) String dueDateStr,
                              Model model) {
        EntityIssue issue = issueService.getIssueById(issueId);
        if (issue != null) {
            issue.setName(name);
            issue.setDescription(description);
            issue.setStatus(status);
            if (dueDateStr != null && !dueDateStr.isEmpty()) {
                issue.setDueDate(LocalDateTime.parse(dueDateStr));
            }
            issue.setUpdatedAt(LocalDateTime.now());
            issueService.saveIssue(issue);
        }

        model.addAttribute("columnId", columnId);
        model.addAttribute("issues", issueService.findByColumnId(columnId));
        model.addAttribute("allIssues", issueService.findByProjectId(projectId));
        return "fragments/issues :: change-block-on-update";
    }

    @DeleteMapping("/hx/v1/issue/delete")
    public String deleteIssue(@RequestParam("issueId") Long issueId,
                              @RequestParam("column_id") Long columnId,
                              @RequestParam("project_id") Long projectId,
                              Model model) {
        issueService.deleteIssueById(issueId);

        model.addAttribute("columnId", columnId);
        model.addAttribute("issues", issueService.findByColumnId(columnId));
        model.addAttribute("allIssues", issueService.findByProjectId(projectId));
        return "fragments/issues :: change-block-on-update";
    }
}