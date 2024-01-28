public class EmployeeFactory {
	    public static Employee createPartTimeEmployee(String name, String email, String address, String dob, String job,String emptype, double baseSalary, double hours) {
	        SalaryStrategy salaryStrategy = new Hourly(hours);
	        return new PartTime(name, email, address, dob, job, emptype, baseSalary, hours, salaryStrategy);
	    }

	    public static Employee createFullTimeEmployee(String name, String email, String address, String dob, String job, String emptype, double salary) {
	        SalaryStrategy salaryStrategy = new Monthly();
	        return new FullTime(name, email, address, dob, job, emptype, salary, salaryStrategy);
	    }
	}
