package game.main;
import game.employees.BarMan;
import game.employees.Chef;
import game.employees.ExperienceLevel;
import game.employees.Waiter;
import game.kitchen.Menu;
import game.mealorder.Table;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
	private double score;
	private HighScoreList highScoreList;

	public Player player;
	public Restaurant restaurant;
	public Integer day;
	public static String userInput;

	public void start( ) {
		gameStart();
		ArrayList<Waiter> WaitersList = new ArrayList<Waiter>();
		Waiter w1 = new Waiter("Josh", "kola", "12345", 300,
				ExperienceLevel.Low);
		Waiter w2 = new Waiter("Molly", "Coola", "12345", 300,
				ExperienceLevel.Low);
		Waiter w3 = new Waiter("Kate", "Jones", "12345", 300,
				ExperienceLevel.Low);
		BarMan b1 = new BarMan("Bobo", "Coola", "1234", 400,
				ExperienceLevel.Low);
		Chef c1 = new Chef("Samoa", "Jones", "1234", 500, ExperienceLevel.Low);

		// Add employees to lists
		WaitersList.add(w1);
		WaitersList.add(w2);
		WaitersList.add(w3);

		// create tables
		//initialize collection
		restaurant.availableBudget = 10000;
		restaurant.tables = new ArrayList<Table>();
		for (int i = 0; i < 9; i++) {
			Table table = new Table(i, null, false, null, null);
			//add each table
			restaurant.tables.add(table);
		}
		
		day = 1;
		System.out.println("Lets go to the Chambers"
				+ "\nDay : "+ day+""
				+ "\nLets have a chat with Employees.");
		// assigntables for each waiter
		restaurant.assignTablesToServingWaiters(WaitersList);
		
		//lets set the menu
		System.out.println("Lets Set the Quality and the Price of our Menu");
		Menu menu = new Menu();
		
//		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7 };
//        foreach (int number in numbers.TakeRandom(rnd, 3))
//        {
//            Console.WriteLine(number);
//        }
		
		//user sets the quality & price of the Menu
		

		// populate clients to restaurant
		// populate
	}

	private void foreach(Class<Integer> class1) {
		// TODO Auto-generated method stub
		
	}

	private void gameStart( ) {
		player = new Player(null, null, highScoreList, null);
		restaurant = new Restaurant();
		System.out
				.println("\n __  __                 _   _        	"
						+ "\n|  \\/  |_   _ _ __ __ _| |_(_)_ __   __ _"
						+ "\n| |\\/| | | | | '__/ _` | __| | '_ \\ / _` |"
						+ "\n| |  | | |_| | | | (_| | |_| | | | | (_| |"
						+ "\n|_|  |_|\\__,_|_|  \\__,_|\\__|_|_| |_|\\__,_|"
						+ "\nIt is a beautiful autumn in a cold northern country of Estonia."
						+ "\nAlmost all pale yellow leaves are fallen from birches and maples."
						+ "\nIn the creeping winter students roam in their coats and hats."
						+ "\nIn this setting, you have decided to open up a new restaurant."
						+ "\n+++++++++++++++++++++++++++++++++++++++++++++++++"
						+ "\nWhat is your name, brave entrepreneur?");
		// Set Player & Restaurant Name
		player.name = collectInput();
		System.out.println("What name do you want for your restaurant "
				+ player.name + "?");
		restaurant.name = collectInput();
		System.out.println("What city will you open your restaurant in,"
				+ player.name + "?");
		restaurant.city = collectInput();
		System.out.println("What is the address of your restaurant, "
				+ player.name + "?");
		restaurant.address = collectInput();
		//restaurant.player = player;
		System.out.println("+++++++++++++++++++++++++++++++");
		System.out.println("" + "Welcome," + player.name + ". "
				+ restaurant.name + " has officially Opened in "
				+ restaurant.city + "");
	}

	public String collectInput( ) {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		try {
			do {
				userInput = reader.readLine();
			} while (userInput.isEmpty());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return userInput;
	}
}
