
/**
 * @(#) Beverage.java
 */

public class Beverage extends MenuItem
{
	public Integer volume;
	
	private Menu menu;
	
	public Beverage(String name, double price, QualityLevel quality) {
		super(name, price, quality,ingredientCost);
		// TODO Auto-generated constructor stub
	}
	
	public Beverage(String name, double price, QualityLevel quality,
			Integer volume) {
		super(name, price, quality,ingredientCost);
		this.volume = volume;
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
