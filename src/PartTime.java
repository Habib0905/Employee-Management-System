
public class PartTime extends Employee {
        private double hours;
        private double baseSalary;
	public PartTime(String name, String email, String address, String dob, String job, String emptype, double baseSalary, double hours,
			SalaryStrategy salaryStrategy) {
		super(name, email, address, dob, job,emptype, 0, salaryStrategy);
		this.hours = hours;
                this.baseSalary = baseSalary;
                
               
		((Hourly) getSalaryStrategy()).setHours(hours);
                ((Hourly) getSalaryStrategy()).setBaseSalary(baseSalary);
		
		setSalary(findSalary());
	}
	 public double getHours() {
	        return hours;
	    }
         public double getBaseSalary() {
	        return baseSalary;
	    }
         
         @Override
public String toString() {
    return "Part-Time Employee: " +
           "ID: " + getId() +
           ", Name: " + getName() +
           ", Email: " + getEmail() +
           ", Address: " + getAddress()+
           ", Dob: " +getDob() +
           ", Job: " + getJob() +
           ", Base Salary " + getBaseSalary()+
           ", Hours: " + getHours() +
           ", Salary: "+ getSalary() +
           
           "\n";
}
	
}
