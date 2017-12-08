package dac.cma.controller;

import dac.cma.model.Person;
import dac.cma.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/home")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

//    acessing data
    @PostMapping("/add") // Map ONLY GET Requests
    public String addNewUser (@ModelAttribute Person person) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        personRepository.save(person);
        return "redirect:/all";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("listPerson", personRepository.findAll());
        return "all";
    }

//    form submission
    @GetMapping("/person")
    public String greetingForm(Model model) {
        model.addAttribute("person", new Person());
        return "greeting";
    }
}