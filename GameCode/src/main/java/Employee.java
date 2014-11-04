import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Employee extends Person
{
	private static final String UNDETERMINED_MSG = "Cannot determine cost for employee training.";
	public static final int MAX_WAITER_TABLES = 3;

	private ExperienceLevel experience;

	private Set<Table> servicedTables = new LinkedHashSet<>();
	
	private EmployeeType employeeType;

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

	public Set<Table> getServicedTables() {
		return servicedTables;
	}

	public boolean isWaiter() {
		return EmployeeType.WAITER.equals(employeeType);
	}

	public boolean isBarman() {
		return EmployeeType.BARMAN.equals(employeeType);
	}

	public boolean isChef() {
		return EmployeeType.CHEF.equals(employeeType);
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
		if (ExperienceLevel.HIGH.equals(experience)) {
			throw new IllegalStateException("Attempt to increase experience for Employee with maximum experience level");

		} else if (ExperienceLevel.MEDIUM.equals(experience)) {
			this.experience = ExperienceLevel.HIGH;
		} else if (ExperienceLevel.LOW.equals(experience)) {
			this.experience = ExperienceLevel.MEDIUM;
		} else
			throw new IllegalStateException("Unable to increase Employee experience (not set or non-handled ExperienceLevel)");

		return true;
	}

	// base satisfaction percentage with this employees' services
	public int baseLineClientSatisfactionPercentage() {
		if (isWaiter()) {
			switch (experience) {
				case HIGH:
							return 90;
				case MEDIUM:
							return 80;
				case LOW:
							return 60;
			}
		}
		switch (experience) {
			case HIGH:
				return 80;
			case MEDIUM:
				return 60;
			case LOW:
				return 40;
		}

		throw new IllegalStateException("Should have returned by now");
	}

	public Integer getSalary() {
		if (null == employeeType)
			throw new IllegalStateException("Employee type not set, no salary known.");

		if (null == experience)
			throw new IllegalStateException("Employee experience level not set, no salary known.");

		int baseSalary = EmployeeType.WAITER.equals(employeeType) ? 200 : 300;
		if (ExperienceLevel.HIGH.equals(experience)) {
			return baseSalary + 200;
		}  else if (ExperienceLevel.MEDIUM.equals(experience)) {
			return baseSalary + 100;
		}

		return baseSalary;
	}

	public String titleString() {
		return employeeType.name().substring(0,1) + employeeType.name().substring(1).toLowerCase();
	}

	public String experienceString() {
		if (ExperienceLevel.HIGH.equals(experience)) {
			return "is highly experienced";
		} else {
			return "is with " + experience.name().toUpperCase() +  " experience";
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb
				.append(titleString()).append(" ")
				.append(name).append(" ")
				.append(surname).append(" ")
				.append("(").append(phoneNo).append(")")
				.append(" ").append(experienceString()).append(", ");

		sb.append("current SALARY is ").append(getSalary());

		if (taxCode != null) {
			sb.append(" and tax-code is ").append(taxCode);
		}
		sb.append(".");

		return sb.toString();
	}
}
