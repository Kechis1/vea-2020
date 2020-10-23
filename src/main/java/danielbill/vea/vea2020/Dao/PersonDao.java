package danielbill.vea.vea2020.Dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import danielbill.vea.vea2020.Entities.Person;
import danielbill.vea.vea2020.Entities.PersonMapper;


@Repository
public class PersonDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert personInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        personInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("People").usingGeneratedKeyColumns("id")
                .usingColumns("first_name", "last_name", "age");
    }

    @PostConstruct
    public void postConstructor() {
        try {
            String dbProducerName;
            try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
                DatabaseMetaData metaData = con.getMetaData();
                dbProducerName = metaData.getDatabaseProductName();
            }
            String sqlCreateTable;
            if ("Apache Derby".equals(dbProducerName)) {
                sqlCreateTable = "CREATE TABLE People (id INTEGER NOT NULL "
                        + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                        + "first_name varchar(255), last_name varchar(255), "
                        + "age int";
            } else if ("H2".equals(dbProducerName)) {
                sqlCreateTable = "create table People(id INTEGER NOT NULL " + "AUTO_INCREMENT," + " first_name varchar(255), "
                        + "last_name varchar(255), " + "age int);";
            } else {
                throw new RuntimeException("Unsupported database type");
            }
            jdbcTemplate.update(sqlCreateTable);
        } catch (DataAccessException e) {
            System.out.println("Table already exists.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Person insert(Person person) {
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(person);
        Number key = personInsert.executeAndReturnKey(source);
        person.setId(key.longValue());
        return person;
    }

    public List<Person> getAll(){
        return jdbcTemplate.query("select * from People", new PersonMapper());
    }

    public Person find(long id) {
        return jdbcTemplate.queryForObject("select * from People where id = ?", new Object[] {Long.valueOf(id)} , new PersonMapper());
    }

}
