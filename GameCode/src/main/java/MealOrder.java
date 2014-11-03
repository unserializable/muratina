

/**
 * @(#) MealOrder.java
 */

public class MealOrder
{
	private Employee employee;
	
	public Beverage orderedBeverage;
	
	public Dish orderedDish;
	
	private Table table;
	
	public MealOrder() {
		
	}
	
	public Beverage getBeverage(){
		return orderedBeverage;
	}

	public Dish getDish() {
		return orderedDish;
	}
	
	public Table getTable(){
		return table;
	}
	
	
}
