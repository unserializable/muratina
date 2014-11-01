import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {
private static String userInput;
	
	public static void main(String args[])
	{
			Restaurant restaurant = new Restaurant();
			Player player  = new Player();
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
	   		player.name = collectInput();
	   		System.out.printf("What name do you want for your restaurant %s?",userInput);
	   		collectInput();
	   		System.out.printf("What city will you open your restaurant in, %s?",userInput);
	   		collectInput();
	   		
	   		
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
