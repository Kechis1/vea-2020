package danielbill.vea.vea2020.Services;

import danielbill.vea.vea2020.Controllers.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;

@Service
@RequestScope
public class PersonService {

    private static int counter = 0;

    @Autowired
    private UniversityService universityService;

    public PersonService() {
        counter++;
        System.out.println("Constructor PersonService");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct PersonService");
    }

    public void savePerson(Person person) {
        System.out.println(person);
        System.out.println("PersonService " + counter);
    }
}
