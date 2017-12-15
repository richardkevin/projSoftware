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

@Controller
public class AdminController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeacherService teacherService;
    
    @GetMapping("/semester-release")
    public String projects(HttpSession session, Model model) {
        User userLogged = (User) session.getAttribute("userLogged");
        session.setAttribute("loginError", null);

        if (userLogged == null || !userLogged.getUsername().equals("Admin")) {
            session.setAttribute("loginError", "Acesso não autorizado");
            return "redirect:/login";
        }

        projectService.releaseEndSemester();

        return "redirect:/index";
    }

}
