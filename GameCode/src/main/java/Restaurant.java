
import java.util.ArrayList;
import java.util.Random;

/**
 * @(#) Restaurant.java
 */
public class Restaurant {
	public String address;
	
	public int UTILITYCOSTS = 4000;
	
	public double availableBudget;

	public String city;

	public ArrayList<Client> clients;

	public double employeePercentageAverage;

	public ArrayList<Employee> employees;

	public String name;

	public Player player;

	public double reputation;

	public ArrayList<Table> tables;

	public ArrayList<Waiter> waitersList;
	
	public Game game;
	
	public Restaurant() {
		// initial budget is 10000
		// initial reputation is 15
		//game = new Game();
		availableBudget = 10000;
		reputation = 15;
		tables = new ArrayList<Table>();
		clients = new ArrayList<Client>();
		employees = new ArrayList<Employee>();
		//setUpTables();
		createEmployees();
	}

	/**
	 * @param game
	 * @param count
	 * @return collect user input, input a value between 1 & 9 to select the
	 *         table where the waiter should be assigned if input is empty
	 *         request input if input is > 9 or < 1 request input
	 */
	public int assignTablesByUserInput(Game game, int count) {
		int parsedOut;
		do {
			// get input
			game.collectInput();
			
			// convert input to integer
			parsedOut = Integer.parseInt(game.userInput);
			// while parsedut is less than 0 or greater than 9
			if (9 < parsedOut || parsedOut < 1) {
				System.out.println("Please Input a value between 1 & 9" + count
						+ "p:" + parsedOut);
			}
		} while (game.userInput.isEmpty() || (9 < parsedOut || parsedOut < 1));
		return parsedOut;
	}

	/**
	 * @param WaitersList
	 */
	public void assignTablesToServingWaiters() {
		// instantiate game
		// set Waiters for each table
		for (Table table : tables) {

			// each employee can be assigned 3 times
			// please select tables for marco
//			int measure = 0; 
//			if(tables.size() == 9)
//				measure = 3;
//			else if(tables.size() == 5)
//				measure = 2;
//			else if(tables.size()==2)
//				measure = 1;
			int pos = getRandomIndex(3);
			Waiter waiter = waitersList.get(pos);
			int position = tables.indexOf(table)+1;
			table.setServingWaiter(waiter);
			System.out.println("===================================="
					+ "\nPlease Assign :" + waiter.getName() + " to a table");
			System.out.println("++++++++ Table: " + position
						+ "+++++++++++++++");
				printTablesAssignmentList();
		}
		// view the final Assignment
		System.out.println("" + "\n==================================="
				+ "\nGreat Everyone has been Assigned to a Table!"
				+ "\nHere is the Roster"
				+ "\n===================================");
		printTablesAssignmentList();
	}

	public void cleanUpRestaurant() {
		tables = new ArrayList<Table>();
		clients  = new ArrayList<Client>();
		
	}

	private void createEmployees() {
		waitersList = new ArrayList<Waiter>();
		Waiter w1 = new Waiter("Josh", "kola", "12345", 300,
				ExperienceLevel.Low);
		w1.getTrainingCost();
		Waiter w2 = new Waiter("Molly", "Coola", "12345", 300,
				ExperienceLevel.Low);
		w2.getTrainingCost();
		Waiter w3 = new Waiter("Kate", "Jones", "12345", 300,
				ExperienceLevel.Low);
		w3.getTrainingCost();
		BarMan b1 = new BarMan("Bobo", "Coola", "1234", 400,
				ExperienceLevel.Low);
		b1.getTrainingCost();
		Chef c1 = new Chef("Samoa", "Jones", "1234", 500, ExperienceLevel.Low);
		c1.getTrainingCost();

		// Add employees to lists
		waitersList.add(w1);
		waitersList.add(w2);
		waitersList.add(w3);
		employees.add(w1);
		employees.add(w2);
		employees.add(w3);
		employees.add(b1);
		employees.add(c1);
	}

	public double deductSalaries(){
		double totalSalary = 0;
		for (Employee employees : employees) {
			totalSalary += employees.getSalary();
		}
		deductFromAvailableBudget(totalSalary);
		return availableBudget;
	}

	public double getAvailableBudget() {
		return availableBudget;
	}

	public double getCurrentBudget() {
		return 0;
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public double paySuppliers(Menu menu){
		return availableBudget -= menu.gettotalIngredientsCost();
	}
	
	public double addTotalClientsMoney(){
		for (Client client : clients) {
			availableBudget += client.getTotalMoneySpent();
		}
		return availableBudget;
	}

	/**
	 * Print the Assignment of Each table to a waiter Display every waiter that
	 * is assigned to a table
	 */
	public void printTablesAssignmentList() {
		// ++++++++++++++
		// for each table print the table and available waiter
		// 1.table 1: getservingWaiter
		for (Table table : tables) {
			// 1. Table 1:
			int position = tables.indexOf(table) + 1;
			String _waitername = "Unassigned";
			String _waiterLevel = "Unassigned";
			if (table.getServingWaiter() != null) {
				_waitername = table.getServingWaiter().getName();
				_waiterLevel = table.getServingWaiter().getExperienceLevel()
						.toString();
			}
			System.out.println(position + ".Table " + table.getTableNo() + ": "
					+ _waitername + ": Level: " + _waiterLevel);
		}
	}

	public void receiveClients() {
		for (Table table : tables) {
			for (int i = 0; i < 2; i++) {
				Client client = new Client();
				clients.add(client);
				table.clients = new ArrayList<Client>();
				table.clients.add(client);
			}

		}
	}

	/**
	 * @param menu
	 */
	public void serveMealOrdersToClients(Menu menu) {
		for (Table table : tables) {
			int count = 0;
			Waiter waiter = table.getServingWaiter();
			System.out
			.println("\n"
					+ "\n=========================================="
					+ "\n------------------------------------------"
					+ "\n-----Table : "+ table.getTableNo()+ "-----"
					+ "\n"+ waiter.getName()+ " just received two orders"
					);
			
			// clients order a meal
			do {
					double priceDifference = 0;
					StringBuilder selecteditems = new StringBuilder();
					//for each client				
					for (Client client : table.clients) {
						//select random menu item	
						MealOrder mealOrder =  new MealOrder();
						selecteditems.append("\nClient:" + client.getName());
						
						//select random beverage
						int beverageSet = 0;
						do {
							int index = getRandomIndex(menu.menuItems.size());
							MenuItem selectedMenuItem = menu.menuItems.get(index);
							if (selectedMenuItem.getClass() == Beverage.class) {
								mealOrder.orderedBeverage = (Beverage) selectedMenuItem;
								priceDifference = selectedMenuItem.price - selectedMenuItem.ingredientCost;
								if(priceDifference >=3)
									priceDifference = Math.round(priceDifference*100.0);
								beverageSet = 1;
							} 
						} while (beverageSet == 0);
						selecteditems.append("\n-----------------------------------");
						selecteditems.append("\nBeverage: "+ mealOrder.getBeverage().name);
						//select random dish
						int drinkset = 0;
						do {
							int index = getRandomIndex(menu.menuItems.size());
							MenuItem selectedMenuItem = menu.menuItems.get(index);
							if (selectedMenuItem.getClass() == Dish.class) {
								mealOrder.orderedDish = (Dish) selectedMenuItem;
								priceDifference = selectedMenuItem.price - selectedMenuItem.ingredientCost;
								double percentagetoDecrease =0;
								if(priceDifference >=3)
									priceDifference = Math.round(priceDifference*100.0);
									percentagetoDecrease = priceDifference * 10;
									drinkset =1;
							} 
						} while (drinkset == 0);
						selecteditems.append("\nDish : "+ mealOrder.getDish().name);
						selecteditems.append("\n-----------------------------------");
						client.setTotalMoneySpent(mealOrder.orderedDish.getPrice());
					}
					
					// the order is prepared and served
					//
					
					System.out
							.println("\n=========================================="
									+  selecteditems
									+ "\n==========================================");

					//get employee satisfaction percentage average
					employeePercentageAverage = updateEmployeeSatisfactionPercentageAverage();
					
					double clientSatisfaction = 0;
					for (Client client : table.clients) {
						clientSatisfaction = client
								.getClientSatisfactionEvaluation(employeePercentageAverage);
						//menu
						//clientSatisfaction = Math.round(clientSatisfaction*100.0);
						reputation += clientSatisfaction;
					}
					count++;

				} while (count < 2);

		}
	}

	/**
	 * @param menu
	 * @return
	 */
	public int getRandomIndex(int size) {
		Random r =  new Random();
		int index = 0;
		index = r.nextInt(size);
		if(index <=0){
			index = size-(size-2);
		}
		return index;
	}
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}
	/**
	 * Set Up the tables add 9 tables to the Restaurant
	 */
	public void setUpTables() {
		int occupiedTables = 0;
		if(reputation >= 30)
			occupiedTables = 9;
		else if(reputation <30 && reputation >=15)
			occupiedTables = 5;
		else if(reputation <15)
			occupiedTables = 2;
		
		for (int i = 0; i < occupiedTables; i++) {
			int tableNo = i + 1;
			Table table = new Table(tableNo, null, false, null, null);
			// add each table
			tables.add(table);
		}
	}

	public double payUtilityCosts() {
		deductFromAvailableBudget(UTILITYCOSTS);
		return availableBudget;
	}

	/**
	 * @return
	 */
	public double updateEmployeeSatisfactionPercentageAverage() {
		employeePercentageAverage = 0;
		// lets get the satisfaction of clients
		for (Employee employee : employees) {

			if (employee.getClass() == BarMan.class) {
				// calculate probability of client being satisfied
				employee.getExperienceLevel();
				employeePercentageAverage += employee
						.getSatisfactionRate();
			} else if (employee.getClass() == Waiter.class) {
				// calculate probability of client being satisfied
				employee.getExperienceLevel();
				employeePercentageAverage += employee
						.getSatisfactionRate();
			} else if (employee.getClass() == Chef.class) {
				// calculate probability of client being satisfied
				employee.getExperienceLevel();
				employeePercentageAverage += employee
						.getSatisfactionRate();
			}

		}
		// set client satisfaction
		employeePercentageAverage = employeePercentageAverage / 5;
		return employeePercentageAverage;
	}

	public void loadTrainingMenu(String userInputs) {
		if (userInputs == "train") {
			viewTrainMenu();
			for (int i = 0; i < 2; i++) {
				Game game2 = new Game();
				game2.collectInput();
				switch (game2.userInput) {
				case "a":
					System.out.println("===================================="
							+ "\n ++ Please Select who to Train ++");
					for (Waiter waiter : waitersList) {
						int position = employees.indexOf(waiter) + 1;
						System.out.println("\n " + position + " Waiter :"
								+ waiter.getName());
					}
					int input = Integer.parseInt(game2.collectInput());

					if (input <= 3 && input >= 1) {
						Waiter waiter = waitersList.get(input - 1);
						deductFromAvailableBudget(waiter.train(availableBudget));
						i = 1;
						viewTrainMenu();
					}
				case "b":
					BarMan barman = new BarMan();
					for (Employee employee : employees) {
						if (employee.getClass() == BarMan.class) {
							barman = (BarMan) employee;
						}
					}
					i = 1;
					deductFromAvailableBudget(barman.train(availableBudget));
					viewTrainMenu();
				case "c":
					Chef chef = new Chef();
					for (Employee employee : employees) {
						if (employee.getClass() == Chef.class) {
							chef = (Chef) employee;
						}
						i = 1;
					}
					deductFromAvailableBudget(chef.train(availableBudget));
					viewTrainMenu();
				default:
				}
				;
			}
			;
		}
	}

	/**
	 * @param waiterstr
	 */
	public void viewTrainMenu() {
		StringBuilder waiterstr = new StringBuilder();
		for (Waiter waiter : waitersList) {
			int position = employees.indexOf(waiter) + 1;
				waiterstr.append("\n " + position + " Waiter :"
						+ waiter.getName());
		}
		System.out.println("-----------------------------------"
				+ "\nWelcome to WORKFASTER Cooking School,"
				+ "\nHere are the Costs:"
				+ "\n a. To Train Waiters = 800€"
				+ waiterstr.toString()
				+ "\n b. To Train BarMan = 1200€"
				+ "\n c. To Train Chef   = 1200€"
				+ "\n 4. Return To Restaurant"
				+ "\nSelect Employees To Trains"
				+ city + "");
	}

	/**
	 * @param barman
	 */
	public void deductFromAvailableBudget(double value) {
		availableBudget -= value;
	}
	}
