package ru.bluewhale.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bluewhale.entity.Person;

import java.util.List;

/**
 * private static final String URL = "jdbc:mysql://localhost:3306/employee_directory";
 * private static final String USER = "admin";
 * private static final String PASSWORD = "************";
 * <p>
 * private static Connection connection;
 * <p>
 * static {
 * try {
 * Class.forName("com.mysql.cj.jdbc.Driver");
 * } catch (ClassNotFoundException e) {
 * e.printStackTrace();
 * }
 * <p>
 * <p>
 * try {
 * connection = DriverManager.getConnection(URL, USER, PASSWORD);
 * } catch (SQLException e) {
 * e.printStackTrace();
 * }
 * }
 */
@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    private static int PEOPLE_INDEX;
    private List<Person> people;


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * people = new ArrayList<>();
     * <p>
     * try {
     * Statement statement = connection.createStatement();
     * String SQL = "SELECT * FROM employee";
     * System.out.println("SQL: " + SQL);
     * ResultSet rs = statement.executeQuery(SQL);
     * while (rs.next()) {
     * people.add(new Person(
     * rs.getInt("id"),
     * rs.getString("first_name"),
     * rs.getString("last_name"),
     * rs.getString("email")
     * )
     * );
     * }
     * <p>
     * } catch (SQLException throwables) {
     * System.err.println("SQL exception: " + throwables.getMessage());
     * throwables.printStackTrace();
     * }
     *
     * @return people;
     */
    public List<Person> index() {

        return jdbcTemplate.query("SELECT * FROM employee", new PersonMapper());
    }

    /**
     * @param id Person person = null;
     *           try {
     *           String SQL = "SELECT * FROM employee WHERE id=?";
     *           PreparedStatement statement = connection.prepareStatement(SQL);
     *           statement.setInt(1, id);
     *           ResultSet rs = statement.executeQuery();
     *           <p>
     *           rs.next();
     *           <p>
     *           person = new Person(
     *           rs.getInt("id"),
     *           rs.getString("first_name"),
     *           rs.getString("last_name"),
     *           rs.getString("email")
     *           );
     *           <p>
     *           } catch (SQLException throwables) {
     *           System.err.println("SQL exception: " + throwables.getMessage());
     *           throwables.printStackTrace();
     *           }
     *           <p>
     * @return person;
     */
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM employee WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class)).stream()
                .findAny()
                .orElse(null);
    }

    /**
     * @param person String SQL = "INSERT INTO employee VALUES (default ,?,?,?)";
     *               try {
     *               PreparedStatement statement = connection.prepareStatement(SQL);
     *               System.out.println("SQL: " + SQL);
     *               statement.setString(1, person.getFirstName());
     *               statement.setString(2, person.getLastName());
     *               statement.setString(3, person.getEmail());
     *               statement.executeUpdate();
     *               <p>
     *               } catch (SQLException throwables) {
     *               System.err.println("SQL exception: " + throwables.getMessage());
     *               throwables.printStackTrace();
     *               }
     */
    public void save(Person person) {
        Object[] rawPerson = new Object[]{person.getFirstName(), person.getLastName(), person.getEmail()};

        jdbcTemplate.update("INSERT INTO employee VALUES (default ,?,?,?)",
                person.getFirstName(), person.getLastName(), person.getEmail());
    }


    /**
     * @param id
     * @param person String SQL = "UPDATE employee SET first_name=?, last_name=?, email = ? WHERE id = ?";
     *               try {
     *               PreparedStatement statement = connection.prepareStatement(SQL);
     *               statement.setString(1, person.getFirstName());
     *               statement.setString(2, person.getLastName());
     *               statement.setString(3, person.getEmail());
     *               statement.setInt(4, id);
     *               statement.executeUpdate();
     *               <p>
     *               } catch (SQLException throwables) {
     *               System.err.println("SQL exception: " + throwables.getMessage());
     *               throwables.printStackTrace();
     *               }
     */
    public void update(int id, Person person) {
        jdbcTemplate.update(
                "UPDATE employee SET first_name=?, last_name=?, email = ? WHERE id = ?",
                person.getFirstName(), person.getLastName(), person.getEmail(), person.getId()
        );
    }

    /**
     * @param id String SQL = "DELETE FROM employee WHERE id = ?";
     *           try {
     *           PreparedStatement statement = connection.prepareStatement(SQL);
     *           statement.setInt(1, id);
     *           statement.executeUpdate();
     *           } catch (SQLException throwables) {
     *           System.err.println("SQL exception: " + throwables.getMessage());
     *           throwables.printStackTrace();
     *           }
     */
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM employee WHERE id = ?", id);
    }
}
