
/**
 * @(#) Dish.java
 */

public class Dish extends MenuItem
{
	
	public Integer calorieCount;
	
	private Menu menu;
	
	public Dish() {
		// TODO Auto-generated constructor stub
	}

	public Dish(String name, double price, QualityLevel quality,
			double ingredientCost) {
		super(name, price, quality, ingredientCost);
		// TODO Auto-generated constructor stub
	}

	private MealOrder mealOrder;
	
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
