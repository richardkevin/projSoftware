package dac.cma.controller;

import dac.cma.model.Student;
import dac.cma.service.ProjectService;
import dac.cma.service.StudentService;
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
    private ProjectService projectService;
    @Autowired
    private StudentService studentService;

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

}
