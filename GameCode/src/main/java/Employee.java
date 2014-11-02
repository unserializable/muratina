/**
 * @(#) Employee.java
 */
public class Employee
{
	private String name;
	
	private String surname;
	
	private String phoneNo;
	
	private int salary;
	
	private ExperienceLevel experienceLevel;

	public Employee(String name, String surname, String phoneNo, int salary,
			ExperienceLevel experienceLevel) {
		this.name = name;
		this.surname = surname;
		this.phoneNo = phoneNo;
		this.salary = salary;
		this.experienceLevel = experienceLevel;
	}
	
	public ExperienceLevel getExperienceLevel( )
	{
		return null;
	}
	
	public boolean increaseExperience( )
	{
		return false;
	}
	
	
}
