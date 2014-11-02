package game.mealorder;

import game.kitchen.MealOrder;
import game.kitchen.Menu;

/**
 * @(#) Client.java
 */
public class Client
{
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
	
	private Integer avgDishCalorieCount;
	
	private Integer avgBeverageVolume;
	
	private double totalMoneySpent;
	
	private String taxCode;
	
	

	private MealOrder mealOrder;
	
	
	private Menu menu;
	
	
}
