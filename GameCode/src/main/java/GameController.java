import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GameController {
	private static final Map<EmployeeType, Integer> EMPLOYEE_COUNTS;
	private static final List<String> ORDER_NOS;

	private static final SecureRandom RANDOM = new SecureRandom();

	static {
		Map<EmployeeType, Integer> empCounts = new HashMap<>();
		empCounts.put(EmployeeType.BARMAN, 1);
		empCounts.put(EmployeeType.CHEF, 1);
		empCounts.put(EmployeeType.WAITER, 3);
		EMPLOYEE_COUNTS = empCounts;

		List<String> orderNos = new ArrayList<>(32);
		for (int i = 0; i < 32; i++) {
			int mod = i % 10;
			switch (mod) {
				case 1 : orderNos.add(i, String.valueOf(i) + "st"); break;
				case 2 : orderNos.add(i, String.valueOf(i) + "nd"); break;
				case 3 : orderNos.add(i, String.valueOf(i) + "rd"); break;
				default :
					orderNos.add(String.valueOf(i) + "th");
			}
		}
		ORDER_NOS = Collections.unmodifiableList(orderNos);
	}

	private static final String MATCH_EVERYTHING = ".+";
	private static final String MATCH_INTEGER = "(\\d+)";
	private static final Pattern CMD_EXTRACTOR_PATTERN = Pattern.compile("(\\d+)\\..*");

	private static final String AA_GAME_NAME = "\n __  __                 _   _        	"
			+ "\n|  \\/  |_   _ _ __ __ _| |_(_)_ __   __ _"
			+ "\n| |\\/| | | | | '__/ _` | __| | '_ \\ / _` |"
			+ "\n| |  | | |_| | | | (_| | |_| | | | | (_| |"
			+ "\n|_|  |_|\\__,_|_|  \\__,_|\\__|_|_| |_|\\__,_|";

	private static final String AA_BANKRUPTCY =
			"#                    #                             m                 \n" +
			"#mmm    mmm   m mm   #   m   m mm  m   m  mmmm   mm#mm   mmm   m   m \n" +
			"#\" \"#  \"   #  #\"  #  # m\"    #\"  \" #   #  #\" \"#    #    #\"  \"  \"m m\" \n" +
			"#   #  m\"\"\"#  #   #  #\"#     #     #   #  #   #    #    #       #m#  \n" +
			"##m#\"  \"mm\"#  #   #  #  \"m   #     \"mm\"#  ##m#\"    \"mm  \"#mm\"   \"#   \n" +
			"                                          #                     m\"\n" +
			"                                          \"                    \"\"";

	private static final String GAME_LEGEND =
			"It is a beautiful autumn in a cold northern country of Estonia."
			+ "\nAlmost all pale yellow leaves are fallen from birches and maples."
			+ "\nIn the creeping winter students roam in their coats and hats."
			+ "\nAnd you have decided to open up a new cozy restaurant.";

	private static final String HIGHSCORE_LIST_HEADER = "VIGOROUS ENTREPRENEURS SO FAR";
	private static final String MENU_LIST_HEADER_PREFIX = " * * * * * ";
	private static final String MENU_LIST_HEADER_SUFFIX  = MENU_LIST_HEADER_PREFIX;
	private static final String MENU_LIST_FOOTER_PREFIX = " < < < < < ";
	private static final String MENU_LIST_FOOTER_SUFFIX  = MENU_LIST_FOOTER_PREFIX;


	private static final String OUTPUT_SEPARATOR =
			"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";

	private static final String PRESS_ENTER_TO_CONTINUE = "Press <Enter> to continue";

	private static final String MAIN_USER_MENU =
			"1. Start New Game\n" +
			"2. View High Score List\n" +
			"42. Exit Game\n";

	private static final String MENU_ITEM_QUALITY_LEVEL_MENU =
			"1. Low \n" +
			"2. High\n";

	private static void showLogo() {
		System.out.println(AA_GAME_NAME);
	}


	// TODO: remove
	private static Menu quickTestMenu() {
		Menu testMenu = new Menu();
		for (Dish d: SimulationGenerator.rndTestDishes()) {
			testMenu.addMenuItem(d);
		}

		for (Beverage b: SimulationGenerator.rndTestBeverages()) {
			testMenu.addMenuItem(b);
		}

		return testMenu;
	}

	public static void main(String args[])
	{
		showLogo();
		gameLoop();
	}

	private static void gameLoop() {
		while (true) {
			String mainMenuCommand = getUserInput(MAIN_USER_MENU);
			if ("42".equals(mainMenuCommand)) {
				userOpExit();
			} else if ("2".equals(mainMenuCommand)) {
				mainUserMenuOpHighScoreList();
			} else if ("1".equals(mainMenuCommand)) {
				mainUserMenuOpPlay();
			}
		}
	}

	private static void mainUserMenuOpPlay() {
		// Print greeting legend
		System.out.println(OUTPUT_SEPARATOR);
		System.out.println(GAME_LEGEND);
		System.out.println(OUTPUT_SEPARATOR);

		// initialize player/restaurant and let the game start
		Player player = askPlayerInfo();
		Restaurant restaurant = askRestaurantInfo(player);

		System.out.println(OUTPUT_SEPARATOR);

		Game game = new Game(player, restaurant);

		Set<Employee> staff = createStaff();
		Menu menu = quickTestMenu();  // askRestaurantMenu(game);

		restaurant.setStaff(staff);
		restaurant.setMenu(menu);

		System.out.println(menuToString(menu));

		System.out.println(player.getName() + " has established a new fine-dining restaurant '"  +
				restaurant.getName() + "' at " + restaurant.getCity() + ", " + restaurant.getAddress());

		dailyLoop(game);
	}

	private static void dailyLoop(Game game) {
		boolean gameEnded = false;

		while (!gameEnded) {
			if (game.day == 30) {
				game.getRestaurant().payMonthlyCosts();
				gameEnded = true;

				System.out.println("Game has ended successfully, score was " + game.getScore());
				break;
			}

			if (game.getDay() % 7 == 0) {
				game.getRestaurant().payDebtToSuppliers();
				game.getRestaurant().paySalaries();

				System.out.println("After paying suppliers and employees, '" + game.getRestaurant().getName() + "' budget is " +
						game.getRestaurant().getCurrentBudget());
			}

			if (game.getRestaurant().getCurrentBudget() < 0) {
				showBankruptcy();
				gameEnded = true;
				break;
			}

			game.day += 1;
			dailyCommandInput(game);
			daySimulation(game);
		}
	}

	private static void daySimulation(Game game) {
		Restaurant restaurant = game.getRestaurant();
		int startingBudget = restaurant.getCurrentBudget();
		int startingReputation = restaurant.getReputation();

		int potentiallyOccupiedTableCount = restaurant.isHighReputation() ? 9 : restaurant.isLowReputation() ? 2 : 5;
		Set<Table> servicedTables = new LinkedHashSet<>();
		Map<Table, Employee> waiterTables = new LinkedHashMap<>();

		for (Employee w: restaurant.getWaiters()) {
			servicedTables.addAll(w.getServicedTables());
			for (Table t: w.getServicedTables()) {
				waiterTables.put(t, w);
			}
		}

		// if not enough tables serviced, potential clients just cannot come to eat this day....
		int occupiedTableCount = servicedTables.size() < potentiallyOccupiedTableCount ? servicedTables.size() : potentiallyOccupiedTableCount;
		Set<Client> daysClients = SimulationGenerator.rndCombination(occupiedTableCount * 2, game.getClientPopulation());

		Map<Client, MealOrder> daysClientOrders = new LinkedHashMap<>();

		//  clients choosing random dishes
		for (Client c: daysClients) {
			Beverage b = restaurant.getMenu().getBeverages().get(RANDOM.nextInt(Menu.MAX_BEVERAGES));
			Dish d = restaurant.getMenu().getDishes().get(RANDOM.nextInt(Menu.MAX_DISHES));
			MealOrder order = new MealOrder(b, d);
			daysClientOrders.put(c, order);
		}

		Set<Table> occupiedTables = new HashSet<>();
		Map<Client, Table> daysClientTables = new LinkedHashMap<>();
		Map<Client, Employee> daysClientWaiters = new LinkedHashMap<>();

		// two clients seated at random table
		for (Iterator<Client> it = daysClients.iterator(); it.hasNext(); ) {
			Client c1 = it.next();
			Client c2 = it.next();
			Set<Table> availableTables = new HashSet(servicedTables);
			availableTables.removeAll(occupiedTables);
			Table availableTable = new ArrayList<Table>(availableTables).get(RANDOM.nextInt(availableTables.size()));
			daysClientTables.put(c1, availableTable);
			daysClientTables.put(c2, availableTable);
			occupiedTables.add(availableTable);

			Employee waiter = waiterTables.get(availableTable);
			daysClientWaiters.put(c1, waiter);
			daysClientWaiters.put(c2, waiter);
		}

		System.out.println(OUTPUT_SEPARATOR);

		for (Client c: daysClients) {
			// baseline satisfaction percentages
			int probServiceSatisfaction = daysClientWaiters.get(c).baseLineClientSatisfactionPercentage();
			int probFoodSatisfaction = restaurant.getChef().baseLineClientSatisfactionPercentage();
			int probDrinkSatisfaction = restaurant.getBarman().baseLineClientSatisfactionPercentage();

			MealOrder mealOrder = daysClientOrders.get(c);
			Dish d = mealOrder.getFoodorder();
			Beverage b= mealOrder.getDrinkorder();

			if (d.isHighQuality()) {
				probFoodSatisfaction += 20;
			}

			if (b.isHighQuality()) {
				probDrinkSatisfaction += 20;
			}

			int dishCostPenalty = (d.getPrice() - d.getIngredientCost())/3*10;
			int bevCostPenalty = (b.getPrice() - b.getIngredientCost())/3*10;
			if (dishCostPenalty > 0) {
				probServiceSatisfaction -= dishCostPenalty;
				probFoodSatisfaction -= dishCostPenalty;
				probDrinkSatisfaction -= dishCostPenalty;
			}

			if (bevCostPenalty > 0) {
				probServiceSatisfaction -= bevCostPenalty;
				probFoodSatisfaction -= bevCostPenalty;
				probDrinkSatisfaction -= bevCostPenalty;
			}

			if (probServiceSatisfaction < 0) probServiceSatisfaction = 0;
			if (probFoodSatisfaction < 0) probFoodSatisfaction = 0;
			if (probDrinkSatisfaction < 0) probDrinkSatisfaction = 0;

			if (probServiceSatisfaction > 100) probServiceSatisfaction = 100;
			if (probFoodSatisfaction > 100) probFoodSatisfaction = 100;
			if (probDrinkSatisfaction > 100) probFoodSatisfaction = 100;

			boolean sSatisfied = RANDOM.nextDouble() > (100 - probServiceSatisfaction)/100.0;
			boolean fSatisfied = RANDOM.nextDouble() > (100 - probFoodSatisfaction)/100.0;
			boolean dSatisfied = RANDOM.nextDouble() > (100 - probDrinkSatisfaction)/100.0;
			String sers = sSatisfied ? "was satisfied with service" : "was unsatisfied with service";
			String foos = sSatisfied ? "satisfied with food" : "unsatisfied with food";
			String dris = sSatisfied ? "satisfied with drink" : "unsatisfied with drink";

			System.out.println(c + " was serviced by " +
					employeeToShortString(daysClientWaiters.get(c))
					 + " at table " + daysClientTables.get(c).getTableNo() + ".");
			System.out.println("Ordered '" + d.getName() + "' and '" + b.getName() + "', " + sers + ", " + foos + ", " + dris + ".");

			restaurant.sellMenuItem(d);
			restaurant.sellMenuItem(b);
			restaurant.adjustReputation(sSatisfied, fSatisfied, dSatisfied);
		}

		int endingBudget = restaurant.getCurrentBudget();
		System.out.println("Budget after daily cash count was "  + endingBudget + ", change during day was " + (endingBudget-startingBudget));
		System.out.println("Restaurant reputation is now "  + restaurant.getReputation() + ", change during day was " + (restaurant.getReputation()-startingReputation));
		System.out.println(OUTPUT_SEPARATOR);
	}

	// decisions that can be made to (or have to be made) at the beginning of each business day
	private static void dailyCommandInput(Game game) {
		System.out.println("New dawn has broken on the morning of your " + ORDER_NOS.get(game.day) + " day as restaurant owner.");

		boolean letDayRoll = false;

		while (!letDayRoll) {
			StringBuilder userDayMenu = new StringBuilder();
			Set<Employee> trainable = getTrainableStaff(game);
			if (!trainable.isEmpty()) {
				userDayMenu.append("1. Train employees (").append(staffToShortString(trainable)).append(")\n");
			}
			userDayMenu.append("2. Assign serviced tables to waiters\n");
			userDayMenu.append("6. Let another busy day roll.\n");
			userDayMenu.append("42. Exit the restaurant business!\n");

			Integer command = Integer.valueOf(getUserInput(userDayMenu.toString()));
			switch (command) {
				case 42: userOpExit(); break;
				case 6 : letDayRoll = true; break;
				case 2 : assignWaiterTablesInput(game); break;
				case 1 : trainInput(game); break;
				default:
					throw new IllegalStateException("Should not happen -- unhandled input!");
			}
		}
	}

	private static void assignWaiterTablesInput(Game game) {
		boolean exitAssignment = false;
		List<Table> tables = game.getRestaurant().getTables();
		if (tables.isEmpty())
			throw new IllegalStateException("Restaurant has no tables");

		List<Employee> waiters = game.getRestaurant().getWaiters();

		while (!exitAssignment) {
			Map<Table, Employee> tablesToWaiters = new LinkedHashMap<>();
			for (Employee w: waiters) {
				for (Table t: w.getServicedTables()) {
					tablesToWaiters.put(t, w);
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("Enter the number of the table to assign:\n");
			for (int i = 0; i < tables.size(); i++) {
				Employee assignee = tablesToWaiters.get(tables.get(i));
				sb.append(i+1).append(". ").append(assignee != null ? "<" +  assignee.toString() + " >" : "< unassigned >").append("\n");
			}

			sb.append("33. Done assigning tables... get to work, everybody!\n");
			sb.append("42. This business is not for me...\n");

			Integer command = Integer.valueOf(getUserInput(sb.toString()));

			switch (command) {
				case 42: userOpExit(); break;
				case 33: exitAssignment = true; break;
				default:
				{
					StringBuilder waiterSelectionInstr = new StringBuilder();
					waiterSelectionInstr.append("Select a waiter to attend table ").append(command).append("\n");
					waiterSelectionInstr.append("0. Let that table be unserviced.\n");
					int j = 0;
					for (; j < waiters.size(); j++) {
						if (waiters.get(j).getServicedTables().size() < Employee.MAX_WAITER_TABLES) {
							waiterSelectionInstr.append(j + 1).append(". ").append(waiters.get(j)).append("\n");
						}
					}

					waiterSelectionInstr.append("33. Let that table be as it was.\n");
					waiterSelectionInstr.append("42. This routing is killing me.\n");

					Integer w8r = Integer.valueOf(getUserInput(waiterSelectionInstr.toString()));
					switch (w8r) {
						case 42: userOpExit(); break;
						case 33: break;
						default:
							Table t = tables.get(command-1);
							for (Employee w: waiters) {
								w.getServicedTables().remove(t);
							}
							if (w8r != 0) { // when not unserviced table
								waiters.get(w8r - 1).getServicedTables().add(t);
							}
					}

					break;
				}
			}
		}
	}

	private static void trainInput(Game game) {
		System.out.println("Training your employees seems to be your only resort, so you get going\n");

		boolean exitTraining = false;
		while (!exitTraining) {
			System.out.println("Who to train?\n");
			List<Employee> trainable = new ArrayList(getTrainableStaff(game));
			if (trainable.isEmpty())
				break;

			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (; i < trainable.size(); i++ ) {
				sb.append(i+1).append(". ").append(trainable.get(i)).append(" Training cost is ")
						.append(trainable.get(i).getTrainingCost()).append(" euros.\n");
			}

			sb.append(i+3).append(". Let them learn on the job...\n");
			sb.append("42. Oh what morons I have to work with! I quit!\n");

			Integer command = Integer.valueOf(getUserInput(sb.toString()));
			Employee toTrain = null;
			switch (command) {
				case 42: userOpExit(); break;
				default:
						if (i+3 == command) {
							exitTraining = true;
							break;
						} else {
							toTrain = trainable.get(command-1); break;
						}
			}

			if (toTrain != null) {
				game.getRestaurant().train(toTrain);
				System.out.println(employeeToShortString(toTrain) + " has underwent training and is now " + toTrain.experienceString() + ".");
				System.out.println("'" + game.getRestaurant().getName() +  "' budget is now " + game.getRestaurant().getCurrentBudget());
			}
		}
	}

	private static void mainUserMenuOpHighScoreList() {
		showHighScoreList();
	}

	private static void userOpExit() {
		System.exit(0);
	}

	private static void showBankruptcy() {
		System.out.println("Like many other entrepreneurial ventures, yours has also ended with\n");
		System.out.println(AA_BANKRUPTCY);
	}

	private static void showHighScoreList() {
		System.out.println(HIGHSCORE_LIST_HEADER);
		// TODO: show high scores
		getUserInput(PRESS_ENTER_TO_CONTINUE, "");
	}

	private static Player askPlayerInfo() {
		String name = getUserInput("What is your name, brave entrepreneur?");
		return new Player(name);
	}

	private static Restaurant askRestaurantInfo(Player player) {
		Restaurant restaurant = new Restaurant();

		restaurant
				.setName(getUserInput("How are you going to name your restaurant, "+ player.getName() + "?"))
				.setCity(getUserInput("Which city will your restaurant operate in, " + player.getName() + "?"))
				.setAddress(getUserInput("What is the address of your restaurant, "+ player.getName() + "?"));

		return restaurant;
	}

	private static Menu askRestaurantMenu(Game game) {
		Restaurant restaurant = game.getRestaurant();
		System.out.println("Before opening up, you will take care to prepare an attractive menu for " + restaurant.getName());

		Menu menu = new Menu();
		System.out.println("You have decided to offer " + Menu.MAX_DISHES +  " delicious dishes.");
		for (int i = 0; i < 5; i++) {
			menu.addMenuItem(askDishInfo(i));
		}

		System.out.println("... and to offer  " + Menu.MAX_DISHES +  " funky beverages to sip on.");
		for (int i = 0; i < 5; i++) {
			menu.addMenuItem(askBeverageInfo(i));
		}

		return menu;
	}

	private static Dish askDishInfo(int order) {
		Dish dish = (Dish) askMenuItemInfo(new Dish(), order);

		Integer dishCC = Integer.valueOf(getUserInput("What is the calorie count for dish '" + dish.getName() + "'?"));
		dish.setCalorieCount(dishCC);

		return dish;
	}

	private static Beverage askBeverageInfo(int order) {
		Beverage beverage = (Beverage) askMenuItemInfo(new Beverage(), order);

		Integer bVolume = Integer.valueOf(getUserInput("What is the volume count for beverage '" + beverage.getName() + "'?"));
		beverage.setVolume(bVolume);

		return beverage;
	}

	private static MenuItem askMenuItemInfo(MenuItem menuItem, int order) {
		String itemType = menuItem.getClass().getSimpleName().toLowerCase();

		String itemName = getUserInput("What is the name for the " + ORDER_NOS.get(order) + " " +  itemType + "?");
		String qString = getUserInput("What is the quality level for the '" + itemName + "'?\n" + MENU_ITEM_QUALITY_LEVEL_MENU);
		QualityLevel itemQuality = ("1".equals(qString)) ? QualityLevel.LOW : QualityLevel.HIGH;

		menuItem
				.setName(itemName)
				.setQuality(itemQuality);

		String priceQuestion = "What is the price for the '" + itemName + "' (ingredients cost is " + menuItem.getIngredientCost() + " euros)?";
		Integer itemPrice = Integer.valueOf(getUserInput(priceQuestion, MATCH_INTEGER));

		return menuItem.setPrice(itemPrice);
	}

	/*
	 *  Below are the methods that are pure simulation/generation and do not require user input;
	 */

	// returns employees that are possible to train in current game state (experience not high yet, budget sufficient)
	private static Set<Employee> getTrainableStaff(Game game) {
		return game.getRestaurant().getStaff().stream().filter( // any employees to train?
				e -> (!e.getExperience().equals(ExperienceLevel.High))
						&& e.getTrainingCost() <= game.getRestaurant().getCurrentBudget())
				.collect(Collectors.toSet());
	}

	public static Set<Employee> createStaff() {
		System.out.println("As a newcomer to the restaurant scene, you have managed to hire following people: ");
		System.out.println(OUTPUT_SEPARATOR);

		Set<Employee> staff = SimulationGenerator.rndStaff(EMPLOYEE_COUNTS);
		System.out.println(staffToString(staff));
		System.out.println(OUTPUT_SEPARATOR);

		return staff;
	}

	public static String menuToString(Menu menu) {
		StringBuilder sb = new StringBuilder();
		Set <MenuItem> menuItems = new LinkedHashSet<>();

		int maxMenuNameLen = 0;
		for (MenuItem item: menu.getMenuItems()) {
			maxMenuNameLen = Math.max(maxMenuNameLen, item.menuName().length());
		}

		sb.append(MENU_LIST_HEADER_PREFIX).append(" MENU ").append(MENU_LIST_HEADER_SUFFIX).append("\n");

		sb.append("   FOOD   \n");
		sb.append(menuItemsToString(menu.getDishes(), maxMenuNameLen));
		sb.append("   DRINKS   \n");
		sb.append(menuItemsToString(menu.getBeverages(), maxMenuNameLen));

		sb.append(MENU_LIST_FOOTER_PREFIX).append(" MENU ").append(MENU_LIST_FOOTER_SUFFIX).append("\n");

		return sb.toString();
	}

	public static String menuItemsToString(List<? extends MenuItem> menuItems, int maxMenuNameLen) {
		StringBuilder sb = new StringBuilder();
		for (MenuItem item: menuItems) {
			sb.append("  ");

			String dishMenuName = item.menuName();
			sb.append(dishMenuName);
			for (int i = 0; i < (maxMenuNameLen - dishMenuName.length()) + 2; i++ ) {
				sb.append(' ');
			}
			sb.append(item.menuPrice()).append("\t").append(item.menuSecret());

			sb.append("\n");
		}

		return sb.toString();
	}

	public static String staffToString(Set<Employee> staff) {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Employee> it = staff.iterator(); it.hasNext(); ) {
			Employee employee = it.next();
			sb.append(employee);
			if (it.hasNext())
				sb.append("\n");
		}
		return sb.toString();
	}

	public static String employeeToShortString(Employee e) {
		return e.titleString() + " " + e.getName() +  " " + e.getSurname().substring(0, 1).toUpperCase();
	}

	public static String staffToShortString(Set<Employee> staff) {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Employee> it = staff.iterator(); it.hasNext();) {
			Employee e = it.next();
			sb.append(employeeToShortString(e));
			if (it.hasNext())
				sb.append(", ");
		}

		return sb.toString();
	}

	/*
	* Below are the methods dealing with user input (console I/O).
	* */

	// Gives user the input instructions, returns user input matching expected instructions
	private static String getUserInput(String instructions) {
		String expected = expectedRegExp(instructions);
		return getUserInput(instructions, expected);
	}

	private static String getUserInput(String instructions, String expected) {
		System.out.println(instructions);

		String userInput = null;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		while (userInput == null) {
			try {
				userInput = console.readLine().trim();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
			if (!userInput.matches(expected)) {
				String msg = "anything at all";
				if (MATCH_EVERYTHING.equals(expected))
					msg = expected.replaceAll("\\|", ", ");
				else if (MATCH_INTEGER.equals(expected))
					msg = "some number";
				System.out.println("Pardon me chief, command not understood! Did you want to say '" + msg + "'?");
				userInput = null;
			}
		}

		return userInput;
	}

	// returns the regexp that matches user input expected according to instructions
	private static String expectedRegExp(String instructions) {
		String [] lines = instructions.split("\n");
		StringBuilder re = new StringBuilder();

		for (String line : lines) {
			Matcher m = CMD_EXTRACTOR_PATTERN.matcher(line);
			if (m.matches()) {
				if (re.length() > 0)
					re.append("|");
				re.append(m.group(1));
			}
		}

		if (re.length() == 0) { // no command numbers, assume non-blank input only
			re.append(MATCH_EVERYTHING);
		}

		return re.toString();
	}
}