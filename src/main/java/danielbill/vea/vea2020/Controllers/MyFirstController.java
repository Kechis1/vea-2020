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


    public MyFirstController() {
        counter++;
    }

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("MyFirstController " + counter);
        model.addAttribute("person", new Person());
        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Validated Person person, BindingResult personResult, Model model) {
        if(personResult.hasErrors()) {
            System.out.println(personResult.getAllErrors());
        }
        System.out.println("MyFirstController " + counter);
        personService.savePerson(person);
        model.addAttribute("people", personService.getAll());
        return "index";
    }

    @ModelAttribute("people")
    public List<Person> getAllPeople(){
        mojeTestovaciMetoda();
        return personService.getAll();
    }

    @PostMapping("/selectPerson")
    public String selectPerson(Person person) {
        System.out.println(person);
        return "index";
    }

    public void mojeTestovaciMetoda() {
        System.out.println("pokus");
    }
}
