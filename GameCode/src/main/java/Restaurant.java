
import java.util.ArrayList;

/**
 * @(#) Restaurant.java
 */
public class Restaurant {
	public double getAvailableBudget() {
		return availableBudget;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public String name;

	public String address;

	public String city;

	public double reputation;

	public double availableBudget;
	
	public double employeePercentageAverage;

	public Player player;

	public ArrayList<Client> clients;

	public ArrayList<Table> tables;

	public ArrayList<Employee> employees;

	public ArrayList<Waiter> waitersList;

	public Restaurant() {
		// initial budget is 10000
		// initial reputation is 15
		availableBudget = 10000;
		reputation = 15;
		tables = new ArrayList<Table>();
		//setUpTables();
		createEmployees();
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
			Table table = new Table(i, null, false, null, null);
			// add each table
			tables.add(table);
		}
	}

	public void receiveClients() {
		for (Table table : tables) {
			int clientCount = 0;
			do {
				Client client = new Client();
				clients.add(client);
				table.clients = new ArrayList<Client>();
				table.clients.add(client);
				clientCount++;
			} while (clientCount < 2);

		}
	}

	/**
	 * @param menu
	 */
	public void serveMealOrdersToClients(Menu menu) {
		for (Table table : tables) {
			int count = 0;

			// clients order a meal
			do {
					double priceDifference = 0;
					StringBuilder selecteditems = new StringBuilder();
					//for each client				
					for (Client client : table.clients) {
						ArrayList<MenuItem> items = new ArrayList<MenuItem>();
						//select random menu item	
						MealOrder mealOrder =  new MealOrder();
						selecteditems.append("Client:" + client.getName());
						//select random beverage
						do {
							int index = (int) (Math.random() * menu.menuItems.size());
							MenuItem selectedMenuItem = menu.menuItems.get(index);
							if (selectedMenuItem.getClass() == Beverage.class) {
								mealOrder.orderedBeverage = (Beverage) selectedMenuItem;
								priceDifference = selectedMenuItem.price - selectedMenuItem.ingredientCost;
								if(priceDifference >=3)
									priceDifference = Math.round(priceDifference*100.0);
								break;
							} 
						} while (true);
						selecteditems.append("-----------------------------------");
						selecteditems.append("Beverage: "+ mealOrder.getBeverage().name);
						//select random dish
						do {
							int index = (int) (Math.random() * menu.menuItems.size());
							MenuItem selectedMenuItem = menu.menuItems.get(index);
							if (selectedMenuItem.getClass() == Dish.class) {
								mealOrder.orderedDish = (Dish) selectedMenuItem;
								priceDifference = selectedMenuItem.price - selectedMenuItem.ingredientCost;
								double percentagetoDecrease =0;
								if(priceDifference >=3)
									priceDifference = Math.round(priceDifference*100.0);
									percentagetoDecrease = priceDifference * 10;
								break;
							} 
						} while (true);
						selecteditems.append("Dish : "+ mealOrder.getDish().name);
						selecteditems.append("-----------------------------------");
					}
					
					// the order is prepared and served
					//
					Waiter waiter = table.getServingWaiter();
					System.out
							.println("\n"
									+ "\n=========================================="
									+ "\n------------------------------------------------"
									+ "\n-------Table : "
									+ table.getTableNo()
									+ "-------"
									+ "\n"
									+ waiter.getName()
									+ " just received two orders"
									+ "\n------------------------------------------------"
									+ selecteditems
									+ "\n ..........eating magestically............"
									+ "\n==========================================");

					//get employee satisfaction percentage average
					employeePercentageAverage = updateEmployeeSatisfactionPercentageAverage();
					
					double clientSatisfaction = 0;
					for (Client client : table.clients) {
						clientSatisfaction = client
								.getClientSatisfactionEvaluation(employeePercentageAverage);
						//menu
						clientSatisfaction = Math.round(clientSatisfaction*100.0);
						reputation += clientSatisfaction;
					}
					count++;

				} while (count < 2);

		}
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

	/**
	 * @param WaitersList
	 */
	public void assignTablesToServingWaiters() {
		// instantiate game
		Game game = new Game();
		// set Waiters for each table
		for (Waiter waiter : waitersList) {

			// each employee can be assigned 3 times
			// please select tables for marco
			System.out.println("===================================="
					+ "\nPlease Assign :" + waiter.getName() + " to a table");
			int count = 1;
			do {
				System.out.println("++++++++ Table: " + count
						+ "+++++++++++++++");
				printTablesAssignmentList();
				int convertedInputString;
				// if empty or less than 0 or greater than 9
				// complain and make the user put it again
				convertedInputString = assignTablesByUserInput(game, count);
				// then get the table based on the index
				Table selectedTable = tables.get(convertedInputString - 1);
				// set the waiter
				selectedTable.setServingWaiter(waiter);
				count++;
			} while (count <= 3);
			count = 1;
		}
		// view the final Assignment
		System.out.println("" + "\n==================================="
				+ "\nGreat Everyone has been Assigned to a Table!"
				+ "\nHere is the Roster"
				+ "\n===================================");
		printTablesAssignmentList();
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

	private void createEmployees() {
		waitersList = new ArrayList<Waiter>();
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
		waitersList.add(w1);
		waitersList.add(w2);
		waitersList.add(w3);
	}
	public double deductSalaries(){
		return availableBudget;
	}
	public double paySuppliers(Menu menu){
		return availableBudget = availableBudget - menu.gettotalIngredientsCost();
	}
	public double getCurrentBudget() {
		return 0;
	}
}
