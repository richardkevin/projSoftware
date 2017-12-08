package dac.cma.controller;

import dac.cma.model.Student;
import dac.cma.model.User;
import dac.cma.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dac.cma.repository.UserRepository;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

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
        studentRepository.save(student);
        return "redirect:/login";
    }
    
//    @RequestMapping("/login")
//    public String signIn(@RequestParam("username") String username, @RequestParam("password") String password) {
//        for (Student student : studentRepository.findAll()) {
//            if student.getUsername
//        }
//    }
//    acessing data
//    @PostMapping("/add") // Map ONLY GET Requests
//    public String addNewUser (@ModelAttribute User user) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//        userRepository.save(user);
//        return "redirect:/all";
//    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("listStudent", studentRepository.findAll());
        return "all";
    }
}