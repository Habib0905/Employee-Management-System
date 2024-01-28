
public class Hourly implements SalaryStrategy {
	
	private double hours;
        private double baseSalary;
	
	public Hourly(double hours) {
        this.hours = hours;
    }
	public void setHours(double hours) {
        this.hours = hours;
        }  
        public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
        }
           
        @Override
	public double calculateSalary(double salary) {
		
		salary = baseSalary*hours;
                return salary;
	}

}
