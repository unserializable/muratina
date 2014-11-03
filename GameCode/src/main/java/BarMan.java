

/**
 * @(#) BarMan.java
 */
public class BarMan extends Employee
{
	public final double TRAININGCOST = 1200;
	public BarMan(String name, String surname, String phoneNo, int salary,
			ExperienceLevel experienceLevel) {
		super(name, surname, phoneNo, salary, experienceLevel);
		// TODO Auto-generated constructor stub
	}

	public BarMan() {
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
	
	public void train(Game game){
		if(game.restaurant.availableBudget > TRAININGCOST && (getExperienceLevel() == ExperienceLevel.Low 
				|| getExperienceLevel() == ExperienceLevel.Medium)){
			if (getExperienceLevel() == ExperienceLevel.Low) {
				setExperienceLevel(ExperienceLevel.Medium);
			}else if(getExperienceLevel() == ExperienceLevel.Medium){
				setExperienceLevel(ExperienceLevel.High);
			}
			increaseExperience();
			game.restaurant.deductFromAvailableBudget(TRAININGCOST);
					System.out.println("\n Barman: "+ getName()+" is now level: " +getExperienceLevel().toString());
					
		}
		else{
					System.out.println("\n"
							+ "\n Sorry. You do not have Enough Money to Train"+ getName());
		}
	}
}
