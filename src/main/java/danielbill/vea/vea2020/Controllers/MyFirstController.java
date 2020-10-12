package danielbill.vea.vea2020.Controllers;

import danielbill.vea.vea2020.Entities.Person;
import danielbill.vea.vea2020.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyFirstController {

    private static int counter = 0;

    @Autowired
    private PersonService personService;

    public MyFirstController() {
        counter++;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Validated Person person, BindingResult personResult) {
        if (personResult.hasErrors()) {
            System.out.println(personResult.getAllErrors());
        }
        System.out.println("MyFirstControler " + counter);
        personService.savePerson(person);
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("MyFirstController " + counter);
        model.addAttribute("person", new Person());
        return "index";
    }

    @PostMapping("/selectPerson")
    public String selectPerson(Person person) {
        System.out.println(person);
        return "index";
    }

    @ModelAttribute("people")
    public List<Person> getAllPeople() {
        return personService.getAll();
    }
}
