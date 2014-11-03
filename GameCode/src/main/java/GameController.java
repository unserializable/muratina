import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameController {
	private static final Map<EmployeeType, Integer> EMPLOYEE_COUNTS;
	private static final List<String> ORDER_NOS;

	static {
		Map<EmployeeType, Integer> empCounts = new HashMap<>();
		empCounts.put(EmployeeType.BARMAN, 1);
		empCounts.put(EmployeeType.CHEF, 1);
		empCounts.put(EmployeeType.WAITER, 3);
		EMPLOYEE_COUNTS = empCounts;

		List<String> orderNos = new ArrayList<>(29);
		for (int i = 0; i < 29; i++) {
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
		for (Dish d: SimulationGenerator.getTestDishes()) {
			testMenu.addMenuItem(d);
		}

		for (Beverage b: SimulationGenerator.getTestBeverages()) {
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

	private static void dailyLoop(Game game) {
		if (game.getDay() % 7 == 0) {
			if (game.getDay() == 28) {
				// this is the end game
			} else {
				// deduct salaries, etc
			}
		}

		// new day starts

		game.day += 1;
		System.out.println("New dawn has broken on the morning of your " + ORDER_NOS.get(game.day) + " day as restaurant owner.");

		StringBuilder dailyMenuString = new StringBuilder();

		// any employees to train ?
		Set<Employee> trainable = game.getRestaurant().getStaff().stream().filter(
				e -> (!e.getExperience().equals(ExperienceLevel.High)) && e.getTrainingCost() < game.getRestaurant().getCurrentBudget())
				.collect(Collectors.toSet());
		if (!trainable.isEmpty()) {
			dailyMenuString.append("1. Train employees (").append(staffToShortString(trainable)).append(")\n");
		}

		dailyMenuString.append("2. Assign serviced tables to waiters\n");
		dailyMenuString.append("6. Let another busy day roll!\n");
		dailyMenuString.append("42. Exit the restaurant business!\n");

		System.out.println(dailyMenuString.toString());

		// and the time rolls on and on ...
		dailyLoop(game);
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

	private static void mainUserMenuOpHighScoreList() {
		showHighScoreList();
	}

	private static void userOpExit() {
		System.exit(0);
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

	public static String staffToShortString(Set<Employee> staff) {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Employee> it = staff.iterator(); it.hasNext();) {
			Employee e = it.next();
			sb.append(e.titleString()).append(" ");
			sb.append(e.getName()).append(" ").append(e.getSurname().substring(0, 1).toUpperCase());
			if (it.hasNext()) sb.append(", ");
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