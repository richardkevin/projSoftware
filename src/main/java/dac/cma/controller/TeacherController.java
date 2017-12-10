package dac.cma.controller;

import dac.cma.model.Teacher;
import dac.cma.model.User;
import dac.cma.service.TeacherService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {
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
}
