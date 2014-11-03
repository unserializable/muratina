

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

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

			//set up tables
			restaurant.setUpTables();
			
			// assign tables to each waiter
			restaurant.assignTablesToServingWaiters();

			// lets set the menu
			System.out.println("\n"
					+ "\n=========================================="
					+ "\nLets create " + restaurant.name + "'s Menu"
					+ "\n=========================================="
					);
			Menu menu = new Menu();

			if ((day == 1) || (day == 7) || (day == 14) || (day == 21)) {
				// if day 1, 7, 14, 21set menu items
				
				// lets add dishes
				createDishes(menu);
				// lets add beverages
				createBeverages(menu);
				// lets set up the cost of the dishes
				setUpMenuItemsCost(menu);
				//lets see the menu
				displayWeeklyMenu(menu);
				
				//pay Supplier on Day 7
				if ((day == 1) || (day == 7) || (day == 14) || (day == 21))
					restaurant.availableBudget = restaurant.availableBudget- 100;
				//each day we have 18 different clients selected.
				
			}
			
			System.out.println("\n"
					+ "\n=========================================="
					+ "\n Great Everything is All set. "
					+ "\n The doors will open in a few Seconds "
					+ "\n" + restaurant.name + "'s Menu"
					+ "\n=========================================="
					);
			//initial budget is 10000
			//initial reputation is 15
			//day 1
			restaurant.receiveClients();
			
			//clients sit down on each table
			restaurant.serveMealOrdersToClients(menu);
			
			
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
	 * Collect cost of High quality Dishes
	 * Collect cost of High quality Drinks
	 * Collect cost of low quality Dishes
	 * Collect cost of High quality Dishes
	 */
	public void setUpMenuItemsCost(Menu menu) {
		double highQualitydishesCost;
		double lowQualitydishesCost;
		double highQualitybeverageCost;
		double lowQualitybeverageCost;
		System.out.println("\n-----------------------------"
				+ "\n=========================================="
				+ "\nOk, now Lets Setup the Menu Item Costs. "
				+ "\n=========================================="
				+ "\nHow much for the High Quality Dishes?"
				);
		//set up costs
		highQualitydishesCost = collectDoubleInput();
		System.out.println("\n-----------------------------"
				+ "\n"
				+ "\nHow much for the Low Quality Dishes?");
		lowQualitydishesCost = collectDoubleInput();
		System.out.println("\n-----------------------------"
				+ "\n"
				+ "\nHow much for the High Quality Beverages?");
		highQualitybeverageCost = collectDoubleInput();
		
		System.out.println("\n-----------------------------"
				+ "\n"
				+ "\nHow much for the Low Quality Beverages?");
		lowQualitybeverageCost = collectDoubleInput();

		for (MenuItem menuitem : menu.menuItems) {
			if(menuitem.getClass()== Beverage.class){
				switch (menuitem.quality) {
				case High:
					menuitem.setPrice(highQualitybeverageCost);
					break;
				case Low:
					menuitem.setPrice(lowQualitybeverageCost);
					break;
				default:
					menuitem.setPrice(lowQualitybeverageCost);
					break;
				}
			}else if(menuitem.getClass()== Dish.class){
				switch (menuitem.quality) {
				case High:
					menuitem.setPrice(highQualitydishesCost);
					break;
				case Low:
					menuitem.setPrice(lowQualitydishesCost);
					break;
				default:
					menuitem.setPrice(lowQualitydishesCost);
					break;
				}
			}
		}
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
			System.out.println("\n#+" + position + "." + menuitem.getName()+ "----> Price: € "+menuitem.getPrice()+"----> Quality: "+ menuitem.getQuality());
		}
	}

	/**
	 * @param menu
	 *            Create the dishes add them to the menu
	 */
	public void createDishes(Menu menu) {
		do {
			int position = menu.menuItems.size()+1;
			Dish dish = new Dish("Empty", 0, QualityLevel.Low, 0, 0);
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
			menu.addMenuItems(dish);
			System.out.println("++Great! Dish "+dish.name+" has Successfully been created\n");
		} while (menu.menuItems.size() <= 4);
		System.out.println("\nAdding of Dishes Complete!");
	}

	/**
	 * @param menu
	 *            create the beverages add them to the menu
	 */
	public void createBeverages(Menu menu) {
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
			menu.addMenuItems(beverage);
			System.out.println("++Great! beverage "+beverage.name+" has Successfully been created");
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
	
	public Double collectDoubleInput() {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		double value = 0;
		try {
			do {
				userInput = reader.readLine();
				value = Double.parseDouble(userInput);
			} while (userInput.isEmpty() || value < 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;
	}
	
}
