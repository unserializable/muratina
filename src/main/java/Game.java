import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;

public class Game
{
	public static final int POPULATION_SIZE = 18;

	// game always starts on day 0
	public int day = 0;

	private final Set<Client> population = SimulationGenerator.rndPopulation(POPULATION_SIZE);

	private final Restaurant restaurant;
	private final Player player;

	public Game(Player player, Restaurant restaurant) {
		this.player = player;
		this.restaurant = restaurant;
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

	public Set<Client> getClientPopulation() {
		return Collections.unmodifiableSet(population);
	}
}
