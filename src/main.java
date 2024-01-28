import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        
        EmployeeDAO employeeDAO = EmployeeDAO.getInstance();
        
//---------------------------------------------------------------------------------------------------------------------------------------

  //Create an Employee 
//        EmployeeFactory employeeFactory = new EmployeeFactory();
//        Employee partTimeEmployee = employeeFactory.createPartTimeEmployee("John Doe", "john@example.com", "123 Main St", "1990-01-15", "Developer", "Part-Time", 500.00, 20.0);
//        
//        employeeDAO.insertEmployee(partTimeEmployee);
//
//       
//        Employee fullTimeEmployee = employeeFactory.createFullTimeEmployee("Jane Smith", "jane@example.com", "456 Elm St", "1985-03-20", "Manager", "Full-Time", 60000.00);
//        employeeDAO.insertEmployee(fullTimeEmployee);

//---------------------------------------------------------------------------------------------------------------------------------------
        //getEmployee by id
        
        
//        int employeeIdToRetrieve = 14; 
//
//        
//        Employee retrievedEmployee = employeeDAO.getEmployee(employeeIdToRetrieve);
//
//        if (retrievedEmployee != null) {
//            
//            System.out.println("Employee Name: " + retrievedEmployee.getName());
//            
//
//            
//            if (retrievedEmployee instanceof PartTime) {
//                PartTime partTimeEmp = (PartTime) retrievedEmployee;
//                System.out.println("Part-Time Employee - Hours: " + partTimeEmp.getHours());
//                System.out.println("Part-Time Employee - Base Salary: " + partTimeEmp.getBaseSalary());
//                System.out.println("Part-Time Employee - Salary: " + partTimeEmp.getSalary());
//            } else if (retrievedEmployee instanceof FullTime) {
//                FullTime fullTimeEmp = (FullTime) retrievedEmployee;
//                System.out.println("Full-Time Employee - Salary: " + fullTimeEmp.getSalary());
//           
//            }
//        } else {
//            System.out.println("Employee not found with ID: " + employeeIdToRetrieve);
//        }

       //---------------------------------------------------------------------------------------------------------------
          //get all employee
               

//        List<Employee> allEmployees = employeeDAO.getAllEmployee();
//
//        for (Employee employee : allEmployees) {
//            System.out.println(employee.toString()); 
//        }
       //-----------------------------------------------------------------------------------------------------------------
        //update employee
    
    
    int employeeIdToUpdate = 19; 
    
    EmployeeFactory employeeFact = new EmployeeFactory();
    Employee partTimeEmpl = employeeFact.createPartTimeEmployee("Bitch Doe", "john@example.com", "123 Main St", "1990-01-15", "Developer", "Part-Time", 500.00, 20.0);

    employeeDAO.updateEmployee(employeeIdToUpdate, partTimeEmpl);
    //-----------------------------------------------------------------------------------------------------------------------
    
    //delete
    
    
//    int employeeIdToDelete = 14; 
//
//    
//    employeeDAO.deleteEmployee(employeeIdToDelete);
//
//    System.out.println("Employee deleted successfully.");
    //----------------------------------------------------------------------------------------------------------------------------

    }
}





