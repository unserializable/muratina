

import java.util.Random;

/**
 * @(#) Client.java
 */
public class Client
{
	private String name;
	private String surname;
	private String phoneNumber;
	
	private Integer avgDishCalorieCount;
	
	private Integer avgBeverageVolume;
	
	private double totalMoneySpent;
	
	private String taxCode;
	
	

	private MealOrder mealOrder;
	
	
	public Client() {
		generateClientData();
	}

	private Menu menu;
	
	public void generateClientData(){
		Random r = new Random();

		int i1 = r.nextInt(8); // returns random number between 0 and 7
		int i2 = r.nextInt(8);
		int i3 = r.nextInt(8);
		int i4 = r.nextInt(742); // returns random number between 0 and 741
		int i5 = r.nextInt(10000); // returns random number between 0 and 9999

		phoneNumber = String.format("%d%d%d-%03d-%04d", i1, i2, i3, i4, i5);
		taxCode = String.format("tc-%03d-%04d", i1, i2, i3, i4, i5);
		
		
		String[] names = {"Sophia","Emma","Olivia","Isabella","Mia","Ava","Lily","Zoe","Emily","Chloe","Layla","Madison","Madelyn","Abigail"};
		String[] surnames = {"Addison","Riley","Harper","Aria","Arianna","Mackenzie","Lila","Evelyn","Adalyn","Grace","Brooklyn","Ellie","Anna"};
		
		int index = (int) (Math.random() * names.length);
		name = names[index];
		surname = surnames[index];
		
	}
	public Integer getAvgDishCalorieCount( ) {
		return avgDishCalorieCount;
	}

	public Integer getAvgBeverageVolume( ) {
		return avgBeverageVolume;
	}

	public double getTotalMoneySpent( ) {
		return totalMoneySpent;
	}

	public String getTaxCode( ) {
		return taxCode;
	}
	
}
