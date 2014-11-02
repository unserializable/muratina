package game.kitchen;
/**
 * @(#) Beverage.java
 */

public class Beverage extends MenuItem
{


	public Integer volume;
	
	private Menu menu;
	
	private double ingredientCost;
	
	private MealOrder mealOrder;
	
	public Beverage(String name, double price, QualityLevel quality) {
		super(name, price, quality);
		// TODO Auto-generated constructor stub
	}
}
