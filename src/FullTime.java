

public class FullTime extends Employee {

	public FullTime(String name, String email, String address, String dob, String job,String emptype, double salary,
			SalaryStrategy salaryStrategy) {
		super(name, email, address, dob, job, emptype, salary, salaryStrategy);
		
	}
        
        @Override
public String toString() {
    return "Full-Time Employee: " +
           "ID: " + getId() +
           ", Name: " + getName() +
           ", Email: " + getEmail() +
           ", Address: " + getAddress()+
           ", Dob: " +getDob() +
           ", Job: " + getJob() +
           ", Salary: "+ getSalary() +
           
           "\n";
}

}
