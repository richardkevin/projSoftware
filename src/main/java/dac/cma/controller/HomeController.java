package dac.cma.controller;

import dac.cma.model.Student;
import dac.cma.model.Teacher;
import dac.cma.service.ProjectService;
import dac.cma.service.StudentService;
import dac.cma.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("projects", projectService.getProjects(5));
        return "index";
    }
    
    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("userLogged") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String username, @RequestParam String password) {
        Student student = studentService.login(username, password);

        if (student != null) {
            session.setAttribute("userLogged", student);
            session.setAttribute("loginError", null);
            return "redirect:/";
        } else {
            Teacher teacher = teacherService.login(username, password);
            if (teacher != null) {
                session.setAttribute("userLogged", teacher);
                session.setAttribute("loginError", null);
                return "redirect:/";
            }
        }
        session.setAttribute("loginError", "Usuário ou senha incorretos");
        return "redirect:/login";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
      session.invalidate();
      return "redirect:/login";
    }
}