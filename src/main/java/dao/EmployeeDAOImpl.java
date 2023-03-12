package dao;

import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {

        try (PreparedStatement statement = connection.prepareStatement(Queries.INSERT.query)) {

            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity_id());

            statement.executeQuery();

        } catch (SQLException e) {
        }
    }

    @Override
    public Employee getById(int id) {

        String sql = "SELECT * FROM employee WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityId = resultSet.getInt("city_id");
                return new Employee(id, firstName, lastName, gender, age, cityId);
            }

        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployee() {

        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(Queries.GET_ALL.query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityId = resultSet.getInt("city_id");

                employees.add(new Employee(id, firstName, lastName, gender, age, cityId));
            }

        } catch (SQLException e) {
        }
        return employees;
    }

    @Override
    public void updateById(int id, String first_name, String last_name, String gender, int age, int city_id) {

        try (PreparedStatement statement = connection.prepareStatement(Queries.UPDATE.query)) {

            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, gender);
            statement.setInt(4, age);
            statement.setInt(5, city_id);
            statement.setInt(6, id);

            statement.executeQuery();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(Queries.DELETE.query)) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
        }
    }
}

enum Queries {

    GET_ALL("SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id"),
    INSERT("INSERT INTO employee(first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))"),
    DELETE("DELETE FROM employee WHERE id=(?)"),
    UPDATE("UPDATE employee SET first_name=?, last_name=?, gender=?, age=?, city_id=? WHERE id=?");

    String query;

    Queries(String query) {
        this.query = query;
    }
}
