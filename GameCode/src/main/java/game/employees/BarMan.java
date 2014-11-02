package game.employees;
import java.util.ArrayList;

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
			break;
		case Medium:
			super.setSalary(400);
			break;
		case High:
			super.setSalary(500);
			break;
		default:
			super.setSalary(300);
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
