package danielbill.vea.vea2020.Converters;

import danielbill.vea.vea2020.Entities.Person;
import danielbill.vea.vea2020.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter2 implements Converter<String, Person> {
    @Autowired
    private PersonService personService;

    @Override
    public Person convert(String source) {
        if (source == null || source.isEmpty() ) {
            return null;
        }
        try {
            return personService.find(Long.parseLong(source));
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
