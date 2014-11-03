
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
	
	public Beverage(String name, double price, QualityLevel quality,
			Integer volume, double ingredientCost) {
		super(name, price, quality);
		this.volume = volume;
		this.ingredientCost = ingredientCost;
		this.mealOrder = mealOrder;
	}
	/**
	 * @param beverage
	 * @param quality
	 */
	public void calculateIngredientCost(String quality) {
		if (quality.contains("1")) {
			super.quality = QualityLevel.High;
			ingredientCost = 3;
		}else{
			super.quality = QualityLevel.Low;
			ingredientCost = 1;
		}
	}

}
