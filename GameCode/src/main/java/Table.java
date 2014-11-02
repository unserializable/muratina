import java.util.ArrayList;

/**
 * @(#) Table.java
 */
public class Table
{
	private Integer tableNo;
	
	private MealOrder mealOrder;
	
	private Boolean occupied;
	
	ArrayList servingWaiters ;

	private Waiter servingWaiter;

	public Table(Integer tableNo, MealOrder mealOrder, Boolean occupied, ArrayList servingWaiters,
			Waiter servingWaiter) {
		this.tableNo = tableNo;
		this.mealOrder = mealOrder;
		this.occupied = occupied;
		this.servingWaiters = servingWaiters;
		this.servingWaiter = servingWaiter;
	}
	
	
}
