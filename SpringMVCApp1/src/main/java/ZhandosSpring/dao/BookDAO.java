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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void update(Book book, int id) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", book.getName(), book.getAuthor(), book.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
    public void knigaBeru(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", person.getId(), id);
    }
    public void knigaAlu(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?", id);
    }

    public Optional<Person> checkVladelec(int id) {
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON person.id = book.person_id WHERE book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
