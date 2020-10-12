package danielbill.vea.vea2020.Converters;

import danielbill.vea.vea2020.Entities.Person;
import org.springframework.core.convert.converter.Converter;

public class PersonConverter implements Converter<Person, String> {
    @Override
    public String convert(Person source) {
        if(source == null) {
            return "null";
        }
        return Long.toString(source.getId());
    }

}
