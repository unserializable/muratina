/**
 * @(#) Client.java
 */

public class Client extends Person
{
	public ClientStatistics getClientStatistics() {
		return clientStatistics;
	}

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

	private ClientStatistics clientStatistics;
	
	private Integer avgDishCalorieCount;
	
	private Integer avgBeverageVolume;
	
	private double totalMoneySpent;
	
	private String taxCode;


	@Override
	public String toString() {
		return getName() + " " + getSurname();
	}
}
