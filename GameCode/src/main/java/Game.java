import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game
{
	// game always starts on day 0
	public int day = 0;

	private final Restaurant restaurant;
	
	private final Player player;

	public Game(Player player, Restaurant restaurant) {
		this.player = player;
		this.restaurant = restaurant;
	}

	public int getDay() {
		return day;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Player getPlayer() {
		return player;
	}

	public int getScore() {
		return restaurant.getCurrentBudget();
	}
}
