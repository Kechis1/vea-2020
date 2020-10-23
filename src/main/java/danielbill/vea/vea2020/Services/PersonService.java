package danielbill.vea.vea2020.Services;

import danielbill.vea.vea2020.Dao.PersonDao;
import danielbill.vea.vea2020.Entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UniversityService universityService;

    public PersonService() { }

    @PostConstruct
    public void init() {
        personDao.insert(new Person(1, "Pepa", "Novak", 20));
        personDao.insert(new Person(2, "Karla", "Jezek", 24));
        personDao.insert(new Person(3, "Romana", "Drticova", 27));
    }

    public void savePerson(Person person) {
        personDao.insert(person);
    }

    public List<Person> getAll() {
        return personDao.getAll();
    }

    public Person find(long id) {
        return personDao.find(id);
    }
}
