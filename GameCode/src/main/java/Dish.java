/**
 * @(#) Dish.java
 */

public class Dish extends MenuItem
{
	public static final Integer HIGH_COST = 10;
	public static final Integer LOW_COST = 3;

	private Integer calorieCount;

	@Override
	public Integer getIngredientCost() {
		switch (getQuality()) {
			case HIGH: return HIGH_COST;
			case LOW: return LOW_COST;
		}
		throw new IllegalStateException("Unknown Quality Dish (" + getQuality().name() + ")");
	}

	public Integer getCalorieCount() {
		return calorieCount;
	}

	public Dish setCalorieCount(Integer calorieCount) {
		this.calorieCount = calorieCount;
		return this;
	}

	public String menuName() {
		return getName() +  " (" + calorieCount + " kcal)";
	}
}
