import java.util.List;
public class EmployeeManager {
    
    private int id;
    private String name;
    private String email;
    private String password;
    
   EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
   
   public void insertEmployee(Employee employee) {
        employeeDAO.insertEmployee(employee);
    }
   
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }
    
      public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployee();
    }
    
      public void updateEmployee(int employeeId, Employee employee) {
        employeeDAO.updateEmployee(employeeId, employee);
    }
      
       public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
    
}
