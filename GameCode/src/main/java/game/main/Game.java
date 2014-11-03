package game.main;

import game.kitchen.Beverage;
import game.kitchen.Dish;
import game.kitchen.Menu;
import game.kitchen.MenuItem;
import game.kitchen.QualityLevel;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
	private double score;
	private HighScoreList highScoreList;

	public Player player;
	public Restaurant restaurant;
	public Integer day;
	public static String userInput;

	public void start() {
		gameStart();
		// lets simulate a day
		day = 1;
		do {
			System.out.println("Lets go to the Kitchen." + "\nDay : " + day
					+ "" + "\nLets have a chat with All Waiters.");

			// assign tables to each waiter
			restaurant.assignTablesToServingWaiters();

			// lets set the menu
			System.out.println("Lets create " + restaurant.name + "'s Menu");
			Menu menu = new Menu();

			if ((day == 1) || (day == 7) || (day == 14) || (day == 21)) {
				// if day 1, 7, 14, 21set menu items
				// lets add dishes
				createDish(menu);
				// lets add beverages
				createBeverage(menu);
				displayWeeklyMenu(menu);
				
			}
			day++;
		} while (day <= 30);

		// int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		// foreach (int number in numbers.TakeRandom(rnd, 3))
		// {
		// Console.WriteLine(number);
		// }

		// user sets the quality & price of the Menu

		// populate clients to restaurant
		// populate
	}

	

	/**
	 * @param menu
	 */
	public void displayWeeklyMenu(Menu menu) {
		System.out.println("\n++++++++++++++++++++++++"
				+ "\n+       This Weeks     +" + "\n+         Menu         +"
				+ "\n++++++++++++++++++++++++");
		for (MenuItem menuitem : menu.menuItems) {
			int position = menu.menuItems.indexOf(menuitem) + 1;
			System.out.println("\n#+" + position + "." + menuitem.getName()+ "------ > Price: € "+menuitem.getPrice()+"");
		}
	}

	/**
	 * @param menu
	 *            Create the dishes add them to the menu
	 */
	public void createDish(Menu menu) {
		do {
			int position = menu.menuItems.size()+1;
			Dish dish = new Dish("Empty", 0, QualityLevel.Low, 100, 100);
			System.out.println("Enter the name of your Dish "+ position +",Player: "+ player.name
					+ "?");
			dish.name = collectInput();
			System.out.println("What is the Calorie Count , " + player.name
					+ "?");
			String caloriCount = collectInput();
			dish.calorieCount = Integer.parseInt(caloriCount);
			System.out.println("Great! now select the Quality , " + player.name
					+ ":" + "\n1.High" + "\n2.Low");
			String quality = collectInput();
			dish.calculateIngredientCost(quality);
			System.out.println("How much is the "+ dish.name + " " + player.name + "?");
			String price = collectInput();
			dish.price = Double.parseDouble(price);
			menu.addMenuItems(dish);
			System.out.println("++Great! Dish "+dish.name+" has Successfully been created\n");
		} while (menu.menuItems.size() <= 4);
		System.out.println("\nAdding of Dishes Complete!");
	}

	/**
	 * @param menu
	 *            create the beverages add them to the menu
	 */
	public void createBeverage(Menu menu) {
		do {
			int position = menu.menuItems.size()+1;
			Beverage beverage = new Beverage("Empty", 0, QualityLevel.Low, 100,
					100);
			System.out.println("Enter the name of your Beverage " +position+",Player: "+ player.name
					+ "?");
			beverage.name = collectInput();
			System.out.println("What is the Volume , " + player.name + "?");
			String caloriCount = collectInput();
			beverage.volume = Integer.parseInt(caloriCount);
			System.out.println("Great! now select the Quality , " + player.name
					+ ":" + "\n1.High" + "\n2.Low");
			String quality = collectInput();
			beverage.calculateIngredientCost(quality);
			System.out.println("How much is the " +beverage.name+" "+ player.name + "?");
			String price = collectInput();
			beverage.price = Double.parseDouble(price);
			menu.addMenuItems(beverage);
			System.out.println("++Great! Dish "+beverage.name+" has Successfully been created");
		} while (menu.menuItems.size() <= 9);
		System.out.println("\nAdding of Beverages Complete!");
	}

	private void gameStart() {
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
		// restaurant.player = player;
		System.out.println("+++++++++++++++++++++++++++++++");
		System.out.println("" + "Welcome," + player.name + ". "
				+ restaurant.name + " has officially Opened in "
				+ restaurant.city + "");
		System.out.println("Your Available Budget is :"+ restaurant.availableBudget);
	}

	public String collectInput() {
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
