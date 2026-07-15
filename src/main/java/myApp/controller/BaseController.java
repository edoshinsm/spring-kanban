package myApp.controller;

import myApp.model.EntityProject;
import myApp.model.EntityUser;
import myApp.service.ColumnService;
import myApp.service.IssueService;
import myApp.service.ProjectService;
import myApp.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BaseController {

    private final ProjectService projectService;
    private final ColumnService columnService;
    private final IssueService issueService;
    private final UserService userService;

    BaseController(ProjectService projectService, ColumnService columnService, IssueService issueService, UserService userService) {
        this.projectService = projectService;
        this.columnService = columnService;
        this.issueService = issueService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String login,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam(required = false) String patronymic,
                           @RequestParam String email,
                           RedirectAttributes redirectAttributes) {
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Пароли не совпадают");
            return "redirect:/register";
        }
        if (userService.findByUsername(login) != null) {
            redirectAttributes.addFlashAttribute("error", "Логин занят");
            return "redirect:/register";
        }
        userService.registerUser(login, password, firstName, lastName, patronymic, email);
        return "redirect:/login?registered";
    }

    @GetMapping("/")
    public String base(Model model, Authentication authentication) {
        String username = authentication.getName();
        EntityUser currentUser = userService.findByUsername(username);

        List<EntityProject> userProjects = projectService.getProjectsByUserId(currentUser.getId());
        model.addAttribute("projects", userProjects);

        return "kanban";
    }

    @GetMapping("/project")
    public String selectProject(@RequestParam("id") Long projectId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        EntityUser currentUser = userService.findByUsername(username);

        if (!projectService.isUserMemberOfProject(projectId, currentUser.getId())) {
            throw new AccessDeniedException("У вас нет доступа к этому проекту");
        }

        EntityProject project = projectService.getProjectById(projectId);

        List<EntityProject> userProjects = projectService.getProjectsByUserId(currentUser.getId());
        model.addAttribute("projects", userProjects);
        model.addAttribute("selectedProject", project);
        model.addAttribute("columns", columnService.findByProjectId(projectId));
        model.addAttribute("allIssues", issueService.findByProjectId(projectId));
        return "kanban";
    }

    @ModelAttribute("currentUserFullName")
    public String addCurrentUserFullName(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            EntityUser user = userService.findByUsername(username);
            if (user != null) {
                return user.getFirstName() + " " + user.getLastName();
            }
        }
        return "";
    }
}
