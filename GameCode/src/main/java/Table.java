
import java.util.ArrayList;

/**
 * @(#) Table.java
 */
public class Table {
	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public ArrayList<Waiter> getServingWaiters() {
		return servingWaiters;
	}

	public void setServingWaiters(ArrayList<Waiter> servingWaiters) {
		this.servingWaiters = servingWaiters;
	}

	public Waiter getServingWaiter() {
		return servingWaiter;
	}

	public void setServingWaiter(Waiter servingWaiter) {
		this.servingWaiter = servingWaiter;
	}

	public Integer getTableNo() {
		return tableNo;
	}

	private Integer tableNo;

	private MealOrder mealOrder;

	private Boolean occupied;

	ArrayList<Client> clients;
	ArrayList<Waiter> servingWaiters;

	private Waiter servingWaiter;

	public Table(Integer tableNo, MealOrder mealOrder, Boolean occupied,
			ArrayList<Waiter> servingWaiters, Waiter servingWaiter) {
		this.tableNo = tableNo;
		this.mealOrder = mealOrder;
		this.occupied = occupied;
		this.servingWaiters = servingWaiters;
		this.servingWaiter = servingWaiter;
	}

}
