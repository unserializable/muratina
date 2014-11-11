import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @(#) Client.java
 */

public class Client extends Person
{
	private List<MealOrder> mealOrders = new ArrayList<>();

	public void addMealOrder(MealOrder order) {
		mealOrders.add(order);
	}

	public Integer getAvgDishCalorieCount() {
		Long totalDishCalorieCount = 0L;
		for (MealOrder mo: mealOrders) {
			totalDishCalorieCount += mo.getFoodorder().getCalorieCount();
		}

		return ((Long)(totalDishCalorieCount/ mealOrders.size())).intValue();
	}

	public List<MealOrder> peekMealOrders() {
		return Collections.unmodifiableList(mealOrders);
	}

	public Integer getAvgBeverageVolume() {
		Long totalVolume = 0L;
		for (MealOrder mo: mealOrders) {
			totalVolume += mo.getDrinkorder().getVolume();
		}

		return ((Long)(totalVolume/ mealOrders.size())).intValue();
	}

	public Integer getTotalMoneySpent() {
		Integer total = 0;
		for (MealOrder mo: mealOrders) {
			total += (mo.getDrinkorder().getPrice() + mo.getFoodorder().getPrice());
		}
		return total;
	}

	@Override
	public String toString() {
		return getName() + " " + getSurname();
	}
}
