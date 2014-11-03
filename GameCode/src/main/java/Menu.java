import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @(#) Menu.java
 */

public class Menu
{
	public static final Integer MAX_DISHES = 5;
	public static final Integer MAX_BEVERAGES = 5;

	private List<Beverage> beverages = new ArrayList<>(MAX_BEVERAGES);
	private List<Dish> dishes = new ArrayList<>(MAX_DISHES);

	public void addMenuItem(MenuItem item )
	{
		if (item instanceof Beverage && beverages.size() < MAX_BEVERAGES) {
			beverages.add((Beverage) item);
		} else if (item instanceof Dish && dishes.size() < MAX_DISHES) {
			dishes.add((Dish) item);
		} else {
			throw new IllegalStateException("Could not add " + item + " to menu");
		}
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public List<Beverage> getBeverages() {
		return beverages;
	}

	public List<MenuItem> getMenuItems() {
		List<MenuItem> r = new ArrayList<>(dishes.size() + beverages.size());
		r.addAll(dishes);
		r.addAll(beverages);
		return Collections.unmodifiableList(r);
	}
}
