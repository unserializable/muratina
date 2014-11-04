

/**
 * @(#) Employee.java
 */
public class Employee {
	private double trainingCost;

	public double getTrainingCost() {
		return trainingCost;
	}

	public void setTrainingCost(double trainingCost) {
		this.trainingCost = trainingCost;
	}

	private ExperienceLevel experienceLevel;

	private String name;

	private String phoneNo;
	
	private int salary;

	private int satisfactionRate;

	private String surname;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

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

	public String getName( ) {
		return name;
	}

	public int getSalary( ) {
		return salary;
	}

	public int getSatisfactionRate() {
		return satisfactionRate;
	}

	public String getSurname( ) {
		return surname;
	}

	public int increaseExperience( ) {
		return salary;
		
	}

	public void setExperienceLevel( ExperienceLevel experienceLevel ) {
		this.experienceLevel = experienceLevel;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public void setSalary( int salary ) {
		this.salary = salary;
	}

	public void setSatisfactionRate(int satisfactionRate) {
		this.satisfactionRate = satisfactionRate;
	}

	public void setSurname( String surname ) {
		this.surname = surname;
	}
	
	public double train(double availableBudget){
		if(availableBudget > trainingCost && (getExperienceLevel() == ExperienceLevel.Low 
				|| getExperienceLevel() == ExperienceLevel.Medium)){
			if (getExperienceLevel() == ExperienceLevel.Low) {
				setExperienceLevel(ExperienceLevel.Medium);
			}else if(getExperienceLevel() == ExperienceLevel.Medium){
				setExperienceLevel(ExperienceLevel.High);
			}
			increaseExperience();
					System.out.println("\n Employee : "+ getName()+" is now level: " +getExperienceLevel().toString());
					
		}
		else{
					System.out.println("\n"
							+ "\n Sorry. You do not have Enough Money to Train"+ getName());
		}
		return getTrainingCost();
	}

}
