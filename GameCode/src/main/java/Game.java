import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game
{
	private double score;
	static Restaurant restaurant;
	
	private HighScoreList highScoreList;
	
	public Player player;
		
	public Integer day;
	private static String userInput;
	
	public void start( )
	{
		gameStart();
		ArrayList<Employee> Employeelist = new ArrayList<Employee>();
		Waiter w1 = new Waiter("Josh", "kola", "12345", 300,  ExperienceLevel.Low);
		Waiter w2 = new Waiter("Josh", "kola", "12345", 300,  ExperienceLevel.Low);
		Waiter w3 = new Waiter("Josh", "kola", "12345", 300,  ExperienceLevel.Low);
		BarMan b1 = new BarMan("Bobo", "Coola", "1234", 400,  ExperienceLevel.Low);
		Chef c1 = new Chef("Samoa", "Jones", "1234", 500, ExperienceLevel.Low);
		
		//Add employees to lists
		Employeelist.add(w1);
		Employeelist.add(w2);
		Employeelist.add(w3);
		Employeelist.add(b1);
		Employeelist.add(c1);
		
		//create tables
		restaurant = new Restaurant();
		for (int i = 0; i < 9; i++) {
			Table table = new Table(i, null, false, null, null);
			restaurant.tables.add(table);
		}
		
		//assigntables
		do{
			
		}while(true);
		
		//populate clients to restaurant
		//populate 
	}

	private void gameStart( ) {
		player.restaurant = new Restaurant();
		player  = new Player();
		System.out.println(
		  "\n __  __                 _   _        	"
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
		//Set Player & Restaurant Name 
		player.name = collectInput();
		System.out.println("What name do you want for your restaurant "+ player.name + "?");
		player.restaurant.name = collectInput();
		System.out.println("What city will you open your restaurant in," + player.name + "?");
		player.restaurant.city = collectInput();
		System.out.println("What is the address of your restaurant, "+ player.name + "?");
		player.restaurant.address = collectInput();
		player.restaurant.player = player;
		System.out.println("+++++++++++++++++++++++++++++++");
		System.out.println(
				""
				+ "Welcome,"+ player.name +". "+ player.restaurant.name 
						+ " has officially Opened in "
						+ player.restaurant.city + "");
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
