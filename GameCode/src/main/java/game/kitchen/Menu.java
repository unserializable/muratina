package game.kitchen;
import game.main.Player;

import java.util.ArrayList;

/**
 * @(#) Menu.java
 */

public class Menu
{
	public ArrayList<MenuItem> menuItems;
	
	private Player player;
	
	public ArrayList<MenuItem> addMenuItems(MenuItem item){
		menuItems.add(item);
		return menuItems;
	}

	public Menu() {
		menuItems = new ArrayList<MenuItem>();
	}
	
	
}
