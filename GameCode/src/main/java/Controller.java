import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {
private static String userInput;
	static Restaurant restaurant;
	static Player player;
	static Game game = null;
	public static void main(String args[])
	{
			//Game Start
			restaurant = new Restaurant();
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
	   		restaurant.name = collectInput();
	   		System.out.println("What city will you open your restaurant in," + player.name + "?");
	   		restaurant.city = collectInput();
	   		System.out.println("What is the address of your restaurant, "+ player.name + "?");
	   		restaurant.address = collectInput();
	   		restaurant.player = player;
	   		
	   		//Display Start Menu
	   		if (game == null){
	   			System.out.println(
	   					"Welcome,"+ player.name +". "+ restaurant.name 
	   							+ " has officially Opened in "
	   							+ restaurant.city + ""
	   					+ "\nSelect: "
	   					+ "\n1.Start New Game"
	   					+ "\n2.View High Score List"
	   					+ "\n42.Exit Game");
	   		}else{
	   			
	   		}
//	   		     display newline
//	   		     (#Expect User Input ("1|2|42")) AS (cmd)
//	   		     if (cmd) is 1
//	   		   	(#Start Game)
//	   		     else if (cmd) is 2
//	   		   	(#Show High Score List)
//	   		     else if (cmd) is 42
//	   		   	(#Exit Game)

	   		
	   		//simulate Day
	   		SimulationGenerator simulator = new SimulationGenerator();
	   		simulator.start();
	}

	private static String collectInput() {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		try {
			userInput = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return userInput;
	}
}
