import java.util.ArrayList;
import java.util.List;

/**
 * @(#) Client.java
 */

public class Client extends Person
{
	public Integer getAvgDishCalorieCount() {
		return avgDishCalorieCount;
	}

	public Integer getAvgBeverageVolume() {
		return avgBeverageVolume;
	}

	public double getTotalMoneySpent() {
		return totalMoneySpent;
	}

	public String getTaxCode() {
		return taxCode;
	}

	private Integer avgDishCalorieCount;
	
	private Integer avgBeverageVolume;
	
	private double totalMoneySpent;
	
	private String taxCode;

	private List<MealOrder> mealOrders = new ArrayList<>();


	@Override
	public String toString() {
		return getName() + " " + getSurname();
	}

	public void addMealOrder(MealOrder order) {
		mealOrders.add(order);
	}
}
