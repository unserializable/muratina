

/**
 * @(#) Employee.java
 */
public class Employee {
	private String name;

	private String surname;

	private String phoneNo;

	private int salary;

	private ExperienceLevel experienceLevel;

	public Employee( String name, String surname, String phoneNo, int salary, ExperienceLevel experienceLevel ) {
		this.setName(name);
		this.surname = surname;
		this.phoneNo = phoneNo;
		this.salary = salary;
		this.experienceLevel = experienceLevel;
	}

	public ExperienceLevel getExperienceLevel( ) {
		return experienceLevel;
	}

	public String getSurname( ) {
		return surname;
	}

	public int getSalary( ) {
		return salary;
	}

	public void setSurname( String surname ) {
		this.surname = surname;
	}

	public int increaseExperience( ) {
		return salary;
		
	}

	public void setSalary( int salary ) {
		this.salary = salary;
	}

	public void setExperienceLevel( ExperienceLevel experienceLevel ) {
		this.experienceLevel = experienceLevel;
	}

	public String getName( ) {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

}
