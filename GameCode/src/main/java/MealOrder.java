/**
 * @(#) MealOrder.java
 */

public class MealOrder
{
	private Beverage drinkorder;
	private Dish foodorder;

	public MealOrder(Beverage beverage, Dish dish) {
		this.drinkorder = beverage;
		this.foodorder = dish;
	}

	public Dish getFoodorder() {
		return foodorder;
	}

	public Beverage getDrinkorder() {
		return drinkorder;
	}
}
