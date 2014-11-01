/**
 * @(#) Restaurant.java
 */

public class Restaurant
{
	private String name;
	
	private String address;
	
	private String city;
	
	public Integer reputation;
	
	private double availableBudget;
	
	private Player Player;
	
	private Table Table;
	
	public Restaurant() {
		
	}
	public Restaurant(String name, String address, String city,
			Integer reputation, double availableBudget, Player player,
			Table table) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.reputation = reputation;
		this.availableBudget = availableBudget;
		Player = player;
		Table = table;
	}
	public double getCurrentBudget( )
	{
		return 0;
	}
	
	public void train( Employee employee )
	{
		
	}
	
	
}
