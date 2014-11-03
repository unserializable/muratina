import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

	private String name;
	private String address;
	private String city;

	private Menu menu;

	private List<Table> tables = new ArrayList<>(MAX_TABLES);
	private Set<Employee> staff = new LinkedHashSet<>(MAX_EMPLOYEES);

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

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public void train( Employee employee )
	{
		// TODO:
	}
}
