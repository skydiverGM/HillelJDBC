package ua.hillel;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    private final DatabaseConnector connector;

    public EmployeeDAO(DatabaseConnector connector) {
        this.connector = connector;
    }

    public void create(Emloyee employee) {
        String sql = "INSERT INTO employees (name, age, position, salary) VALUES (?,?,?,?)";
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setString(3, employee.getPosition());
            statement.setDouble(4, employee.getSalary());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DatabaseConnectionException("Failed to create Employee: " + e.getMessage());
        }
    }

    public Optional<Emloyee> findById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try(Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return Optional.of(new Emloyee(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("position"),
                            resultSet.getDouble("salary")));
                }
            }
        }catch (SQLException e){
            throw new DatabaseConnectionException("Failed to find Employee: " + e.getMessage());
        }
        return Optional.empty();
    }

    public int update(Emloyee employee) {
        String sql = "UPDATE employees SET name = ?,age = ?,position = ?,salary = ? WHERE id = ?";
        try(Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setString(3, employee.getPosition());
            statement.setDouble(4, employee.getSalary());
            statement.setInt(5, employee.getId());
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new DatabaseConnectionException("Failed to update Employee: " + e.getMessage());
        }
    }

    public int delete(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try(Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new DatabaseConnectionException("Failed to delete Employee: " + e.getMessage());
        }
    }

    public List<Emloyee> findAll() {
        List<Emloyee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try(Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Emloyee employee = new Emloyee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary")
                );
                employees.add(employee);
            }
        }catch (SQLException e){
            throw new DatabaseConnectionException("Failed to find all Employees: " + e.getMessage());
        }
        return employees;
    }
}
