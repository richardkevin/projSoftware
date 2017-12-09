package dac.cma.controller;

import dac.cma.model.Project;
import dac.cma.repository.ProjectRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/add-project")
    public String addProject(HttpSession session, Model model) {
        if (session.getAttribute("userLogged") == null) {
            session.setAttribute("loginError", "Acesso não autorizado");
            return "redirect:/login";
        }
        model.addAttribute("project", new Project());
        return "add-project";
    }

    @PostMapping("/save-project")
    public String addProject(@ModelAttribute Project project) {
        projectRepository.save(project);

        return "redirect:/my-projects";
    }
}
