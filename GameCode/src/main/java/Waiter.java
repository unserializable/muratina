

/**
 * @(#) Waiter.java
 */
public class Waiter extends Employee
{

	public Waiter( String name, String surname, String phoneNo, int salary, ExperienceLevel experienceLevel ) {
		super(name, surname, phoneNo, salary, experienceLevel);
	}

	@Override
	public ExperienceLevel getExperienceLevel() {
		return super.getExperienceLevel();
	}

	@Override
	public int increaseExperience() {
		switch (super.getExperienceLevel()) {
		case Low:
			super.setSalary(200);
			super.setSatisfactionRate(60);
			break;
		case Medium:
			super.setSalary(300);
			super.setSatisfactionRate(80);
			break;
		case High:
			super.setSalary(200);
			super.setSatisfactionRate(90);
			break;
		default:
			super.setSalary(200);
			super.setSatisfactionRate(40);
			break;
		}
		return super.increaseExperience();
	}

	@Override
	public String getName() {
		return super.getName();
	}
	
	
}
