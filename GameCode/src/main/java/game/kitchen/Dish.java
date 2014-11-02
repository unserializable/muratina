package game.kitchen;
/**
 * @(#) Dish.java
 */

public class Dish extends MenuItem
{
	
	public Integer calorieCount;
	
	private Menu menu;
	
	private double ingredientCost;
	
	private MealOrder mealOrder;

	public Dish(String name, double price, QualityLevel quality) {
		super(name, price, quality);
		// TODO Auto-generated constructor stub
	}

}
