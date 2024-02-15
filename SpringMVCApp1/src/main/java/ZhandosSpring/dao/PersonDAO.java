package ZhandosSpring.dao;

import ZhandosSpring.models.Book;
import ZhandosSpring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index () {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(fullName, birthYear) VALUES (?, ?)", person.getFullName(), person.getBirthYear());
    }
    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET fullName=?, birthYear=? WHERE id=?", person.getFullName(), person.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public List<Book> kitaptary(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
    public Optional<Person> showByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM person WHERE fullName=?", new Object[]{fullName}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
