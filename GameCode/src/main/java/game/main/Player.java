package game.main;
import game.kitchen.Menu;

/**
 * @(#) Player.java
 */

public class Player {
	public String name;

	public Restaurant restaurant;

	private HighScoreList score;

	private Menu Menu;

	public Player(String name, Restaurant restaurant, HighScoreList score,
			Menu menu) {
		this.name = name;
		this.restaurant = restaurant;
		this.score = score;
		Menu = menu;
	}
}
