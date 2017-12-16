package dac.cma.controller;

import dac.cma.model.Teacher;
import dac.cma.model.User;
import dac.cma.service.ProjectService;
import dac.cma.service.TeacherService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/add-teacher")
    public String addTeacherForm(HttpSession session, Model model) {
        User userLogged = (User) session.getAttribute("userLogged");
        session.setAttribute("loginError", null);

        if (userLogged == null || !userLogged.getUsername().equals("Admin")) {
            session.setAttribute("loginError", "Acesso não autorizado");
            return "redirect:/login";
        }
        model.addAttribute("teacher", new Teacher());
        return "add-teacher";
    }

    @PostMapping("/save-teacher")
    public String saveTeacher(@ModelAttribute Teacher teacher) {
        teacherService.addTeacher(teacher);
        return "redirect:/login";
    }

    @GetMapping("/project/assign-score/{id}")
    public String assignScore(HttpSession session, @PathVariable long id, Model model) {
        User userLogged = (User) session.getAttribute("userLogged");

        if (userLogged == null) {
            return "redirect:/login";
        }

        model.addAttribute("project", projectService.findProjectById(id));
        return "assign-score";
    }
    
    
    @GetMapping("/project/defense-schedule/{id}")
    public String defenseDchedule(HttpSession session, @PathVariable long id, Model model) {
        User userLogged = (User) session.getAttribute("userLogged");

        if (userLogged == null) {
            return "redirect:/login";
        }

        model.addAttribute("project", projectService.findProjectById(id));
        return "defense-schedule";
    }
}
