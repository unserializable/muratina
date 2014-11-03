
import java.util.ArrayList;

/**
 * @(#) Table.java
 */
public class Table {
	ArrayList<Client> clients;

	private MealOrder mealOrder;

	private Boolean occupied;

	private Waiter servingWaiter;

	ArrayList<Waiter> servingWaiters;

	private Integer tableNo;

	public Table(Integer tableNo, MealOrder mealOrder, Boolean occupied,
			ArrayList<Waiter> servingWaiters, Waiter servingWaiter) {
		this.tableNo = tableNo;
		this.mealOrder = mealOrder;
		this.occupied = occupied;
		this.servingWaiters = servingWaiters;
		this.servingWaiter = servingWaiter;
	}

	public Boolean getOccupied() {
		return occupied;
	}

	public Waiter getServingWaiter() {
		return servingWaiter;
	}

	public ArrayList<Waiter> getServingWaiters() {
		return servingWaiters;
	}

	public Integer getTableNo() {
		return tableNo;
	}
	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public void setServingWaiter(Waiter servingWaiter) {
		this.servingWaiter = servingWaiter;
	}

	public void setServingWaiters(ArrayList<Waiter> servingWaiters) {
		this.servingWaiters = servingWaiters;
	}

}
