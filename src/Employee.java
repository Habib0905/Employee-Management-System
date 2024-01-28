
public class Employee {
        private int id = -1;
	private String name;
	private String email;
	private String address;
	private String dob;
	private String job;
        private String emptype;
	private double salary;
	private SalaryStrategy salaryStrategy;
	
	 public Employee( String name, String email, String address, String dob, String job, String emptype, double salary, SalaryStrategy salaryStrategy) {
	            
		    this.name = name;
		    this.email = email;
		    this.address = address;
		    this.dob = dob;
		    this.job = job;
                    this.emptype = emptype;
                    this.salary = salary;
                    this.salaryStrategy = salaryStrategy;
	    }
	 
	 public void setSalaryStrategy(SalaryStrategy salaryStrategy) {
	        this.salaryStrategy = salaryStrategy;
	    }
	 
          public void setSalary(double salary) {
	        this.salary = salary;
	    }
	 

    public double findSalary() {
        return getSalaryStrategy().calculateSalary(salary);
    }
    
     public void setId(int id){
        this.id = id;
    }
    
    
    public int getId(){
        return id;
    }
    
    
    public String getName() {
    	return name;
    }
    
    public String getEmail() {
    	return email;
    }

    public String getAddress() {
    	return address;
    }

    public String getDob() {
    	return dob;
    }

    public String getJob() {
    	return job;
    }
    
    public String getEmptype(){
        return emptype;
    }
    
    public double getSalary() {
    	return salary;
    }
    
 

	public SalaryStrategy getSalaryStrategy() {
		return salaryStrategy;
	}

}
