package danielbill.vea.vea2020.Controllers;

import danielbill.vea.vea2020.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyFirstController {

    private static int counter = 0;

    @Autowired
    private PersonService personService;
    private List<Person> people = new ArrayList<Person>();

    public MyFirstController() {
        counter++;
    }

    @PostConstruct
    public void init() {
        people.add(new Person("Pepek", "Namornik", 20));
    }

    @PostMapping("/save")
    public String addPerson(@ModelAttribute @Validated Person person, BindingResult personResult) {
        if (personResult.hasErrors()) {

        }
        people.add(person);
        personService.savePerson(person);
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        personService
                .savePerson(new Person("Daniel", "Bill", 1));
        model
                .addAttribute("person", new Person());
        System.out.println("MyFirstController " + counter);
        return "index";
    }

    @ModelAttribute(name = "name")
    public String name() {
        return "Dalajlama";
    }

    @ModelAttribute(name = "people")
    public List<Person> getPeople() {
        return people;
    }
}
