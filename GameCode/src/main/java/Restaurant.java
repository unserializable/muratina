import java.util.ArrayList;

/**
 * @(#) Restaurant.java
 */
public class Restaurant
{
	public String name;
	
	public String address;
	
	public String city;
	
	public Integer reputation;
	
	public double availableBudget;
	
	public Player player;
	
	public ArrayList<Table> tables;
	
	public ArrayList<Employee> employees;

	public Restaurant( ) {
		
	}
	
	public double getCurrentBudget( )
	{
		return 0;
	}
}
