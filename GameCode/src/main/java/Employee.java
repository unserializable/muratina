import java.util.Arrays;

public class Employee extends Person
{
	private static final String UNDETERMINED_MSG = "Cannot determine cost for employee training.";

	private ExperienceLevel experience;
	
	private MealOrder mealOrder;
	
	private TrainingCourse trainingCourse;
	
	private Table table;
	
	private EmployeeType employeeType;
	
	public void setServicedTables( Table tables )
	{
		// THIS IS BOGUS, tables should ^ be array ^ 
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public ExperienceLevel getExperience() {
		return this.experience;
	}

	public void setExperience(ExperienceLevel experience) {
		this.experience = experience;
	}

	public int getTrainingCost()
	{
		if (!Arrays.asList(EmployeeType.values()).contains(employeeType))
			throw new IllegalStateException(UNDETERMINED_MSG);
		if (EmployeeType.Waiter.equals(employeeType))
			return 800;
		else
			return 1200;
	}
	
	public boolean increaseExperience()
	{
		if (ExperienceLevel.High.equals(experience)) {
			throw new IllegalStateException("Attempt to increase experience for Employee with maximum experience level");

		} else if (ExperienceLevel.Medium.equals(experience)) {
			this.experience = ExperienceLevel.High;
		} else if (ExperienceLevel.Low.equals(experience)) {
			this.experience = ExperienceLevel.Medium;
		} else
			throw new IllegalStateException("Unable to increase Employee experience (not set or non-handled ExperienceLevel)");

		return true;
	}

	public Integer getSalary() {
		if (null == employeeType)
			throw new IllegalStateException("Employee type not set, no salary known.");

		if (null == experience)
			throw new IllegalStateException("Employee experience level not set, no salary known.");

		int baseSalary = EmployeeType.Waiter.equals(employeeType) ? 200 : 300;
		if (ExperienceLevel.High.equals(experience)) {
			return baseSalary + 200;
		}  else if (ExperienceLevel.Medium.equals(experience)) {
			return baseSalary + 100;
		}

		return baseSalary;
	} 
}
