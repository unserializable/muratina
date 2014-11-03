package game.main;

import game.employees.BarMan;
import game.employees.Chef;
import game.employees.Employee;
import game.employees.ExperienceLevel;
import game.employees.Waiter;
import game.mealorder.Table;

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

	public Integer reputation;

	public double availableBudget;

	public Player player;

	public ArrayList<Table> tables;

	public ArrayList<Employee> employees;
	
	public ArrayList<Waiter> waitersList;

	public Restaurant() {
		availableBudget = 10000;
		tables = new ArrayList<Table>();
		setUpTables();
		createEmployees();
	}

	/**
	 * Set Up the tables add 9 tables to the Restaurant
	 */
	public void setUpTables() {
		for (int i = 1; i < 10; i++) {
			Table table = new Table(i, null, false, null, null);
			// add each table
			tables.add(table);
		}
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
		System.out.println(""
				+ "\n==================================="
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
				_waiterLevel = table.getServingWaiter().getExperienceLevel().toString();
			}
			System.out.println(position + ".Table " + table.getTableNo() + ": "
					+ _waitername+ ": Level: " + _waiterLevel);
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

	public double getCurrentBudget() {
		return 0;
	}
}
