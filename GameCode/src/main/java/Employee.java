import java.util.Arrays;

public class Employee extends Person
{
	private static final String UNDETERMINED_MSG = "Cannot determine cost for employee training.";

	private ExperienceLevel experience;
	
	private MealOrder mealOrder;
	
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
		if (EmployeeType.WAITER.equals(employeeType))
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

		int baseSalary = EmployeeType.WAITER.equals(employeeType) ? 200 : 300;
		if (ExperienceLevel.High.equals(experience)) {
			return baseSalary + 200;
		}  else if (ExperienceLevel.Medium.equals(experience)) {
			return baseSalary + 100;
		}

		return baseSalary;
	}

	public String titleString() {
		return employeeType.name().substring(0,1) + employeeType.name().substring(1).toLowerCase();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb
				.append(titleString()).append(" ")
				.append(name).append(" ")
				.append(surname).append(" ")
				.append("(").append(phoneNo).append(")");

		if (ExperienceLevel.High.equals(experience)) {
			sb.append(" is highly experienced, ");
		} else {
			sb.append(" is with ").append(experience.name().toUpperCase()).append( " experience, ");
		}

		sb.append("current SALARY is ").append(getSalary());

		if (taxCode != null) {
			sb.append(" and tax-code is ").append(taxCode);
		}
		sb.append(".");

		return sb.toString();
	}
}
