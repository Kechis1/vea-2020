package danielbill.vea.vea2020.Services;

import danielbill.vea.vea2020.Entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private static int counter = 0;
    private List<Person> people = new ArrayList<>();

    @Autowired
    private UniversityService universityService;

    public PersonService() {
        counter++;
        System.out.println("Constructor PersonService");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct PersonService");
        people.add(new Person(1, "Daniel", "Bill", 20));
        people.add(new Person(2, "Pepa", "Novak", 20));
        people.add(new Person(3, "Bill", "Harold", 20));
        people.add(new Person(4, "Carol", "Stand", 20));
    }

    public void savePerson(Person person) {
        System.out.println(person);
        System.out.println("PersonService " + counter);
    }

    public List<Person> getAll() {
        return people;
    }

    public Person find(long id) {
        return people.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
