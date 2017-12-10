package dac.cma.controller;

import dac.cma.model.Project;
import dac.cma.model.Student;
import dac.cma.model.User;
import dac.cma.service.ProjectService;
import dac.cma.service.StudentService;
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
public class StudentController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/sign-in")
    public String signInForm(HttpSession session, Model model) {
        if (session.getAttribute("userLogged") != null) {
            return "redirect:/";
        }
        model.addAttribute("student", new Student());
        return "sign-in";
    }

    @PostMapping("/save-student")
    public String signIn(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/login";
    }

    @GetMapping("/project/request/{id}")
    public String requestAdvisor(HttpSession session, @PathVariable long id, Model model) {
        User userLogged = (User) session.getAttribute("userLogged");
        Project project = projectService.findProjectById(id);

        if (userLogged == null || userLogged.getClass().getSimpleName().equals("Teacher") || project.getOrientador() != null) {
            return "redirect:/my-projects";
        }

        model.addAttribute("project", project);
        model.addAttribute("listTeachers", teacherService.getAllTeachers());

        return "request-advisor";
    }
}
