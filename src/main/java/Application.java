
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.List;

public class Application {


    public static void main(String[] args) throws SQLException {

        // Создаем переменные с данными для подключения к базе
        final String user = "postgres";
        final String pass = "00000";
        final String url = "jdbc:postgresql://localhost:5432/skypro";


        // Создаем соединение с базой с помощью Connection
        // Формируем запрос к базе с помощью PreparedStatement
        try (Connection connection = DriverManager.getConnection(url,user,pass)){

            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            // 5. Удаление конкретного объекта Employee из базы по id
            employeeDAO.deleteById(7);

            // 4. Изменение конкретного объекта Employee в базе по id
            employeeDAO.updateById(7,"Roman", "Romanov", "Male", 57, 3);

            // 3. Получение списка всех объектов Employee из базы
            List<Employee> employees = employeeDAO.getAllEmployee();
            for (Employee employee : employees) {
                System.out.println(employee);
            }

            // 2. Получение конкретного объекта Employee по id
            System.out.println(employeeDAO.getById(7));

            // 1. Создание(добавление) сущности Employee в таблицу
            Employee Pushkin = new Employee(1,"Aleksandr", "Pushkin","Male", 55,1);
            City Surgut = new City(4,"Surgut");
            employeeDAO.create(Pushkin);

        }


    }
}