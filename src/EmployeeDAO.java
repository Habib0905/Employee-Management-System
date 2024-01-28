import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

    private static EmployeeDAO instance;    
    private Connection connection;
    Statement s;
    
    
     private EmployeeDAO() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagementsystem", "root", "hello123");
            s = connection.createStatement();
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
     
     public static EmployeeDAO getInstance() {
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }
     int generatedId = -1;
     public void insertEmployee(Employee employee) {
         
         
        try {
            
            String sql = "INSERT INTO employee (name, email, job, address, dob, salary, emp_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getJob());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setString(5, employee.getDob());
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setString(7, employee.getEmptype());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted == 1) {
                
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    } 
                }
            }

          
            switch (employee) {
                case PartTime partTimeEmployee -> insertPartTimeEmployee(partTimeEmployee);
                case FullTime fullTimeEmployee -> insertFullTimeEmployee(fullTimeEmployee);
                default -> 
                {
                  
                }
                
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private void insertPartTimeEmployee(PartTime partTimeEmployee) throws SQLException {
        String sql = "INSERT INTO part_time (id, name, email, job, address, dob, salary, base_salary, hours) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, generatedId);
        preparedStatement.setString(2, partTimeEmployee.getName());
        preparedStatement.setString(3, partTimeEmployee.getEmail());
        preparedStatement.setString(4, partTimeEmployee.getJob());
        preparedStatement.setString(5, partTimeEmployee.getAddress());
        preparedStatement.setString(6, partTimeEmployee.getDob());
        preparedStatement.setDouble(7, partTimeEmployee.getSalary());
        preparedStatement.setDouble(8, partTimeEmployee.getBaseSalary());
        preparedStatement.setDouble(9, partTimeEmployee.getHours());

        int rowsInserted = preparedStatement.executeUpdate();

    }

    
    private void insertFullTimeEmployee(FullTime fullTimeEmployee) throws SQLException {
        String sql = "INSERT INTO full_time (id, name, email, job, address, dob, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, generatedId);
        preparedStatement.setString(2, fullTimeEmployee.getName());
        preparedStatement.setString(3, fullTimeEmployee.getEmail());
        preparedStatement.setString(4, fullTimeEmployee.getJob());
        preparedStatement.setString(5, fullTimeEmployee.getAddress());
        preparedStatement.setString(6, fullTimeEmployee.getDob());
        preparedStatement.setDouble(7, fullTimeEmployee.getSalary());

        int rowsInserted = preparedStatement.executeUpdate();

       
        
    }
    
    public Employee getEmployee(int id) {
    try {
        
        String sql = "SELECT * FROM employee WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String empType = resultSet.getString("emp_type");
            switch (empType) {
                case "Part-Time":
                    return getPartTimeEmployee(id);
                case "Full-Time":
                    return getFullTimeEmployee(id);
                default:
                    
                    return null;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        
    }

    return null;
}
    
    public Employee getPartTimeEmployee(int id) {
    try {
        
        String sql = "SELECT * FROM part_time WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
           
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String job = resultSet.getString("job");
            String address = resultSet.getString("address");
            String dob = resultSet.getString("dob");
            double salary = resultSet.getDouble("salary");
            double baseSalary = resultSet.getDouble("base_salary");
            double hours = resultSet.getDouble("hours");

            
           EmployeeFactory employeeFactory = new EmployeeFactory();
           Employee partTimeEmployee = employeeFactory.createPartTimeEmployee(name, email, address, dob, job, "Part-Time", baseSalary, hours);
           partTimeEmployee.setId(id);
           return partTimeEmployee;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        
    }

    return null;
}

public Employee getFullTimeEmployee(int id) {
    try {
        
        String sql = "SELECT * FROM full_time WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String job = resultSet.getString("job");
            String address = resultSet.getString("address");
            String dob = resultSet.getString("dob");
            double salary = resultSet.getDouble("salary");

            
             EmployeeFactory employeeFactory = new EmployeeFactory();
             Employee fullTimeEmployee = employeeFactory.createFullTimeEmployee(name, email, address, dob, job, "Full-Time", salary);
             fullTimeEmployee.setId(id);
             return fullTimeEmployee;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        
    }

    return null; 
}

public List<Employee> getAllEmployee() {
    List<Employee> allEmployees = new ArrayList<>();

    try {
        
        String partTimeSql = "SELECT * FROM part_time";
        PreparedStatement partTimeStatement = connection.prepareStatement(partTimeSql);
        ResultSet partTimeResultSet = partTimeStatement.executeQuery();
        while (partTimeResultSet.next()) {
            Employee partTimeEmployee = createPartTimeEmployeeFromResultSet(partTimeResultSet);
            allEmployees.add(partTimeEmployee);
        }

        
        String fullTimeSql = "SELECT * FROM full_time";
        PreparedStatement fullTimeStatement = connection.prepareStatement(fullTimeSql);
        ResultSet fullTimeResultSet = fullTimeStatement.executeQuery();
        while (fullTimeResultSet.next()) {
            Employee fullTimeEmployee = createFullTimeEmployeeFromResultSet(fullTimeResultSet);
            allEmployees.add(fullTimeEmployee);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return allEmployees;
}

private Employee createPartTimeEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    String email = resultSet.getString("email");
    String address = resultSet.getString("address");
    String dob = resultSet.getString("dob");
    String job = resultSet.getString("job");
    double baseSalary = resultSet.getDouble("base_salary");
    double hours = resultSet.getDouble("hours");

    
    EmployeeFactory employeeFactory = new EmployeeFactory();
    Employee partTimeEmployee = employeeFactory.createPartTimeEmployee(name, email, address, dob, job, "Part-Time", baseSalary, hours);
    partTimeEmployee.setId(id);
    return partTimeEmployee;
}

private Employee createFullTimeEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    String email = resultSet.getString("email");
    String address = resultSet.getString("address");
    String dob = resultSet.getString("dob");
    String job = resultSet.getString("job");
    double salary = resultSet.getDouble("salary");

    // Create a Full-Time employee using the EmployeeFactory
    EmployeeFactory employeeFactory = new EmployeeFactory();
    Employee fullTimeEmployee = employeeFactory.createFullTimeEmployee(name, email, address, dob, job, "Full-Time", salary);
    fullTimeEmployee.setId(id);
    return fullTimeEmployee;
}


public void updateEmployee(int employeeId, Employee employee) {
    try {
        String empType = (employee instanceof PartTime) ? "Part-Time" : "Full-Time";
        System.out.println("Employee Type: " + empType);
        // Update the employee table
        String updateEmployeeSQL = "UPDATE employee SET name = ?, email = ?, job = ?, address = ?, dob = ?, salary = ? WHERE id = ?";
        PreparedStatement updateEmployeeStatement = connection.prepareStatement(updateEmployeeSQL);

        updateEmployeeStatement.setString(1, employee.getName());
        updateEmployeeStatement.setString(2, employee.getEmail());
        updateEmployeeStatement.setString(3, employee.getJob());
        updateEmployeeStatement.setString(4, employee.getAddress());
        updateEmployeeStatement.setString(5, employee.getDob());
        updateEmployeeStatement.setDouble(6, employee.getSalary());
        updateEmployeeStatement.setInt(7, employeeId);

        updateEmployeeStatement.executeUpdate();
        updateEmployeeStatement.close();

        if (empType.equals("Part-Time")) {
            // Update the part_time table
            String updatePartTimeSQL = "UPDATE part_time SET name = ?, email = ?, job = ?, address = ?, dob = ?, salary = ?, base_salary = ?, hours = ? WHERE id = ?";
            PreparedStatement updatePartTimeStatement = connection.prepareStatement(updatePartTimeSQL);

            updatePartTimeStatement.setString(1, employee.getName());
            updatePartTimeStatement.setString(2, employee.getEmail());
            updatePartTimeStatement.setString(3, employee.getJob());
            updatePartTimeStatement.setString(4, employee.getAddress());
            updatePartTimeStatement.setString(5, employee.getDob());
            updatePartTimeStatement.setDouble(6, employee.getSalary());
            updatePartTimeStatement.setDouble(7, ((PartTime) employee).getBaseSalary());
            updatePartTimeStatement.setDouble(8, ((PartTime) employee).getHours());
            updatePartTimeStatement.setInt(9, employeeId);

            updatePartTimeStatement.executeUpdate();
            updatePartTimeStatement.close();
        } else if (empType.equals("Full-Time")) {
            // Update the full_time table
            String updateFullTimeSQL = "UPDATE full_time SET name = ?, email = ?, job = ?, address = ?, dob = ?, salary = ? WHERE id = ?";
            PreparedStatement updateFullTimeStatement = connection.prepareStatement(updateFullTimeSQL);

            updateFullTimeStatement.setString(1, employee.getName());
            updateFullTimeStatement.setString(2, employee.getEmail());
            updateFullTimeStatement.setString(3, employee.getJob());
            updateFullTimeStatement.setString(4, employee.getAddress());
            updateFullTimeStatement.setString(5, employee.getDob());
            updateFullTimeStatement.setDouble(6, employee.getSalary());
            updateFullTimeStatement.setInt(7, employeeId);

            updateFullTimeStatement.executeUpdate();
            updateFullTimeStatement.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteEmployee(int id) {
    try {
        
        String empType = getEmployeeType(id);
        if (empType != null) {
            
            String tableName = empType.equals("Part-Time") ? "part_time" : "full_time";
            
            
            String deleteSQL = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            
            
            String deleteCommonSQL = "DELETE FROM employee WHERE id = ?";
            PreparedStatement deleteCommonStatement = connection.prepareStatement(deleteCommonSQL);
            deleteCommonStatement.setInt(1, id);

            deleteCommonStatement.executeUpdate();

            preparedStatement.close();
            deleteCommonStatement.close();
        } else {
            System.out.println("Employee not found with ID: " + id);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public String getEmployeeType(int id) {
    try {
        String sql = "SELECT emp_type FROM employee WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("emp_type");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; 
}





}
    

