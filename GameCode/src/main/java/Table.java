/**
 * @(#) Table.java
 */

public class Table
{
	private Integer tableNo;
	
	private MealOrder mealOrder;
	
	private Boolean occupied;

	public Table(Integer tableNo) {
		this.tableNo = tableNo;
	}

	public Integer getTableNo() {
		return tableNo;
	}
}
