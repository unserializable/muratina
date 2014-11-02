package game.kitchen;
import game.main.Player;

import java.util.ArrayList;

/**
 * @(#) Menu.java
 */

public class Menu
{
	private ArrayList<MenuItem> menuItems;
	
	private Player player;
	
	public ArrayList<MenuItem> addBeverage(MenuItem item){
		menuItems.add(item);
		return menuItems;
	}
	
	
}
