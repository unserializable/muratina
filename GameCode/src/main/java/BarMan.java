

/**
 * @(#) BarMan.java
 */
public class BarMan extends Employee
{
	public BarMan(String name, String surname, String phoneNo, int salary,
			ExperienceLevel experienceLevel) {
		super(name, surname, phoneNo, salary, experienceLevel);
		// TODO Auto-generated constructor stub
	}

	public BarMan() {
		setTrainingCost(1200);
	}

	@Override
	public double getTrainingCost() {
		return 1200;
	}

	@Override
	public ExperienceLevel getExperienceLevel() {
		// TODO Auto-generated method stub
		return super.getExperienceLevel();
	}

	@Override
	public int increaseExperience() {
		switch (super.getExperienceLevel()) {
		case Low:
			super.setSalary(300);
			super.setSatisfactionRate(40);
			break;
		case Medium:
			super.setSalary(400);
			super.setSatisfactionRate(60);
			break;
		case High:
			super.setSalary(500);
			super.setSatisfactionRate(80);
			break;
		default:
			super.setSalary(300);
			super.setSatisfactionRate(40);
			break;
		}
		return super.increaseExperience();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
	
	
}
