
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

	public Dish(String name, double price, QualityLevel quality,
			Integer calorieCount, double ingredientCost) {
		super(name, price, quality);
		this.calorieCount = calorieCount;
		this.ingredientCost = ingredientCost;
	}
	
	/**
	 * @param beverage
	 * @param quality
	 */
	public void calculateIngredientCost(String quality) {
		if (quality.contains("1")) {
			super.quality = QualityLevel.High;
			ingredientCost = 10;
		}else{
			super.quality = QualityLevel.Low;
			ingredientCost = 3;
		}
	}

}
