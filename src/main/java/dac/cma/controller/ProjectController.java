package dac.cma.controller;

import dac.cma.model.Project;
import dac.cma.model.Teacher;
import dac.cma.model.User;
import dac.cma.service.ProjectService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/add-project")
    public String addProject(HttpSession session, Model model) {
        if (session.getAttribute("userLogged") == null) {
            session.setAttribute("loginError", "Acesso não autorizado");
            return "redirect:/login";
        }
        model.addAttribute("project", new Project());
        return "add-project";
    }

    @GetMapping("/project/edit/{id}")
    public String editProject(HttpSession session, @PathVariable long id, Model model) {
        User userLogged = (User) session.getAttribute("userLogged");

        if (userLogged == null) {
            return "redirect:/login";
        }

        model.addAttribute("project", projectService.findProjectById(id));
        return "edit-project";
    }

    @GetMapping("/project/delete/{id}")
    public String deleteProject(HttpSession session, @PathVariable long id) {
        User userLogged = (User) session.getAttribute("userLogged");

        if (userLogged == null || !userLogged.getUsername().equals("Admin")) {
            session.setAttribute("loginError", "Acesso não autorizado");
            return "redirect:/login";
        }

        projectService.delete(projectService.findProjectById(id));
        return "redirect:/my-projects";
    }

    @PostMapping("/save-project")
    public String saveProject(@ModelAttribute Project project) {
        projectService.save(project);

        return "redirect:/my-projects";
    }

    @GetMapping("/my-projects")
    public String myProjects(HttpSession session, Model model) {
        User userLogged = (User) session.getAttribute("userLogged");

        if (userLogged == null) {
            session.setAttribute("loginError", "Acesso não autorizado");
            return "redirect:/login";
        }

        if (userLogged.getClass().getSimpleName().equals("Student")) {
            model.addAttribute(
                "listProjects",
                projectService.getAllStudentProjects(userLogged.getId())
            );
        } else {
            Teacher teacherLogged = (Teacher) userLogged;
            model.addAttribute(
                "listProjects",
                projectService.getAllTeacherProjects(userLogged.getId())
            );
        }

        return "my-projects";
    }
}
