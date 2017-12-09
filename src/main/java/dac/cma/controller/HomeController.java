package dac.cma.controller;

import dac.cma.model.Project;
import dac.cma.model.Student;
import dac.cma.model.Teacher;
import dac.cma.model.User;
import dac.cma.service.ProjectService;
import dac.cma.service.StudentService;
import dac.cma.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
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
    public String home() {
        return "index";
    }

    @GetMapping("/sign-in")
    public String signInForm(Model model) {
        model.addAttribute("student", new Student());
        return "sign-in";
    }
    
    @PostMapping("/save-student")
    public String signIn(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/login";
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