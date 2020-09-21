package danielbill.vea.vea2020.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyFirstController {

    private List<Person> people = new ArrayList<Person>();

    @PostConstruct
    public void init() {
        people.add(new Person("Pepek", "Namornik"));
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute Person person) {
        people.add(person);
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/second")
    public String index2(Model model) {
        return "second";
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
