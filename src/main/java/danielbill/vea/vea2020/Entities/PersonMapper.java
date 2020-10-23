package danielbill.vea.vea2020.Entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(rs.getInt("id"), rs.getString("first_name"),
                rs.getString("last_name"), rs.getInt("age"));
    }

}
