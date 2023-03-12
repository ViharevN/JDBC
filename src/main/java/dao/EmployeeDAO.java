package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    // 1.Создание(добавление) сущности Employee в таблицу
    void create (Employee employee ) throws SQLException;
    // 2.Получение конкретного объекта Employee по id
    Employee getById (int id);
    // 3.Получение списка всех объектов Employee из базы
    List<Employee> getAllEmployee ();
    // 4.Изменение конкретного объекта Employee в базе по id
    void updateById(int id,String first_name, String last_name, String gender, int age, int city_id);
    // 5.Удаление конкретного объекта Employee из базы по id
    void deleteById (int id);

}
