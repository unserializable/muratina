import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @(#) Restaurant.java
 */
public class Restaurant
{
	public static final Integer MAX_TABLES = 9;
	public static final Integer MAX_EMPLOYEES = 5;

	// initial budget
	private int availableBudget = 10000;

	private Integer reputation = 15;

	private Integer debtToSuppliers = 0;

	private String name;
	private String address;
	private String city;

	private Menu menu;

	private List<Table> tables = new ArrayList<>(MAX_TABLES);
	private Set<Employee> staff = new LinkedHashSet<>(MAX_EMPLOYEES);

	{ // dynamic initializer
		for (int i = 0; i < MAX_TABLES; i++) {
			tables.add(new Table(i+1));
		}
	}

	public int getCurrentBudget( )
	{
		return availableBudget;
	}

	public String getName() {
		return name;
	}

	public Restaurant setName(String name) {
		this.name = name;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public Restaurant setAddress(String address) {
		this.address = address;
		return this;
	}

	public Menu getMenu() {
		return menu;
	}

	public Restaurant setMenu(Menu menu) {
		this.menu = menu;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Restaurant setCity(String city) {
		this.city = city;
		return this;
	}

	public Set<Employee> getStaff() {
		return staff;
	}

	public void setStaff(Set<Employee> staff) {
		this.staff = staff;
	}

	public List<Table> getTables() {
		return tables;
	}

	public boolean isHighReputation() {
		return reputation >= 30;
	}

	public boolean isLowReputation() {
		return reputation < 15;
	}

	public boolean isMediumReputation() {
		return !isHighReputation() && !isLowReputation();
	}

	public List<Employee> getWaiters() {
		return staff.stream().filter(e -> e.isWaiter()).collect(Collectors.toList());
	}

	public Employee getChef() {
		for(Employee e: staff) {
			if (EmployeeType.CHEF.equals(e.getEmployeeType()))
				return e;
		}
		return null;
	}

	public Employee getBarman() {
		for(Employee e: staff) {
			if (EmployeeType.BARMAN.equals(e.getEmployeeType()))
				return e;
		}
		return null;
	}


	public void train( Employee employee )
	{
		if ((availableBudget - employee.getTrainingCost()) < 0)
			throw new IllegalStateException("Should not happen in this game: attempted training would decrease budget below zero.");
		availableBudget -= employee.getTrainingCost();
		employee.increaseExperience();
	}

	public Integer getReputation() {
		return reputation;
	}

	public Restaurant payMonthlyCosts() {
		availableBudget -= 4000;
		return this;
	}

	public Restaurant payDebtToSuppliers() {
		availableBudget -= debtToSuppliers;
		debtToSuppliers = 0;
		return this;
	}

	public Restaurant paySalaries() {
		for (Employee e: staff) {
			availableBudget -= e.getSalary();
		}

		return this;
	}

	public Restaurant sellMenuItem(MenuItem item) {
		debtToSuppliers += item.getIngredientCost();
		availableBudget += item.getPrice();

		return this;
	}

	public void adjustReputation(boolean satisfiedService, boolean satisfiedFood, boolean satisfiedDrink) {
		int i = 0;
		i += (satisfiedService ? 1 : -1);
		i += (satisfiedFood ? 1 : -1);
		i += (satisfiedDrink ? 1 : -1);

		reputation += i;
	}
}
