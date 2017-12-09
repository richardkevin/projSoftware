package dac.cma.controller;

import dac.cma.model.Project;
import dac.cma.model.Student;
import dac.cma.repository.ProjectRepository;
import dac.cma.service.ProjectService;
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

}
