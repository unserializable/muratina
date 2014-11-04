
import java.util.Random;

/**
 * @(#) Client.java
 */
public class Client {
	private Integer avgBeverageVolume;
	
	private Integer avgDishCalorieCount;
	
	private MealOrder mealOrder;

	private Menu menu;

	private String name;

	private String phoneNumber;

	private String surname;

	private String taxCode;
	
	private int satisfaction;

	private double totalMoneySpent;

	public Client() {
		generateClientData();
	}

	public void generateClientData() {
		Random r = new Random();

		int i1 = r.nextInt(8); // returns random number between 0 and 7
		int i2 = r.nextInt(8);
		int i3 = r.nextInt(8);
		int i4 = r.nextInt(742); // returns random number between 0 and 741
		int i5 = r.nextInt(10000); // returns random number between 0 and 9999

		phoneNumber = String.format("%d%d%d-%03d-%04d", i1, i2, i3, i4, i5);
		taxCode = String.format("tc-%03d-%04d", i1, i2, i3, i4, i5);

		String[] names = { "Sophia", "Emma", "Olivia", "Isabella", "Mia",
				"Ava", "Lily", "Zoe", "Emily", "Chloe", "Layla", "Madison",
				"Madelyn", "Abigail" };
		String[] surnames = { "Addison", "Riley", "Harper", "Aria", "Arianna",
				"Mackenzie", "Lila", "Evelyn", "Adalyn", "Grace", "Brooklyn",
				"Ellie", "Anna" };

		int nindex = getRandomIndex(names.length);
		name = names[nindex];
		int sindex = getRandomIndex(surnames.length);
		surname = surnames[sindex];

	}

	public Integer getAvgBeverageVolume() {
		return avgBeverageVolume;
	}

	public Integer getAvgDishCalorieCount() {
		return avgDishCalorieCount;
	}

	public MealOrder getMealOrder() {
		return mealOrder;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public double getTotalMoneySpent() {
		return totalMoneySpent;
	}

	public void setAvgBeverageVolume(Integer avgBeverageVolume) {
		this.avgBeverageVolume = avgBeverageVolume;
	}

	public void setAvgDishCalorieCount(Integer avgDishCalorieCount) {
		this.avgDishCalorieCount = avgDishCalorieCount;
	}

	public void setMealOrder(MealOrder mealOrder) {
		this.mealOrder = mealOrder;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public void setTotalMoneySpent(double totalMoneySpent) {
		this.totalMoneySpent = totalMoneySpent;
	}
	
	/**
	 * Randomize client satisfaction
	 */
	public double getClientSatisfactionEvaluation(double percentageAverage) {
		double num = getRandomIndex(100);
		double satistfaction = 0;
		if(num < percentageAverage)
			satistfaction = -1;
		else if (num > percentageAverage)
			satistfaction =  1;
		return satistfaction;
	}
	
	public int getRandomIndex(int size) {
		Random r =  new Random();
		int index = r.nextInt(size); 
		return index;
	}

}
