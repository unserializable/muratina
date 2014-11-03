
import java.util.ArrayList;

/**
 * @(#) Menu.java
 */

public class Menu
{
	public double totalIngredientsCost;
	
	public ArrayList<MenuItem> menuItems;
	
	private Player player;

	public Menu() {
		menuItems = new ArrayList<MenuItem>();
	}

	public ArrayList<MenuItem> addMenuItems(MenuItem item){
		menuItems.add(item);
		return menuItems;
	}

	public double gettotalIngredientsCost() {
		for (MenuItem menuItem : menuItems) {
			totalIngredientsCost += menuItem.getIngredientCost();
		}
		return totalIngredientsCost;
	}

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	

	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
	
}
