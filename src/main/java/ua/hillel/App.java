package ua.hillel;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = App.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new InputStreamException("Could not find properties file");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new InputStreamException("Could not open config.properties" + e.getMessage());
        }

        DatabaseConnector connector = new DatabaseConnector(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.pass")
        );

        Emloyee employee1 = new Emloyee(1, "Jack", 34, "Jackson", 5400);
        Emloyee employee2 = new Emloyee(2, "Juggernaut", 45, "Json", 6500);
        Emloyee employee3 = new Emloyee(3, "Sicret", 32, "Hibernate", 5500);
        Emloyee employee4 = new Emloyee(4, "", 30, "Unknown", 10000);
        Emloyee employee5 = new Emloyee(5, "Dijkstra", 90, "Mathematics", 8300);
        EmployeeDAO employeeDAO = new EmployeeDAO(connector);

        employeeDAO.create(employee1);
        employeeDAO.create(employee2);
        employeeDAO.create(employee3);
        employeeDAO.create(employee4);
        employeeDAO.create(employee5);

        System.out.println(employeeDAO.findById(employee4.getId()));

        employee4.setName("Sicret");
        employee4.setPosition("Spy");
        employeeDAO.update(employee4);

        employeeDAO.delete(3);

        System.out.println(employeeDAO.findAll());
    }
}
