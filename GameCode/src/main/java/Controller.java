
public class Controller {
private static String userInput;
	static Game game;
	static HighScoreList highScoreList;
	public static void main( String[] args )
	{
			//Game Start
			game = new Game();
	   		viewStartMenu();
	   		loadMenu(); 		
	}

	

	private static void viewStartMenu( ) {
		System.out.println(
				""
				+ "Welcome, to Muratina Retaurant Game"
				+ "\nSelect: "
				+ "\n1.Start New Game"
				+ "\n2.View High Score List"
				+ "\n42.Exit Game");
	}

	private static void loadMenu( ) {
		do {	
					game.collectInput();
					switch (userInput) {
					  case "1":
						  	game.start();
					  case "2": 
						  	highScoreList = new HighScoreList();
//						  	highScoreList.saveToScoreList(player.name + " : " + restaurant.name + " : " + restaurant.availableBudget);
					        highScoreList.getScoreList();
					        System.out.println("00. Home"
					        		+ "\n 0. Back");
					        //break;
					  case "42":
						     System.out.println("Bye Bye");
					         System.exit(0);// they are executed if variable ==  any of the above c's
					        //break;
					  case "0":
						     System.out.println("Bye Bye");
					         System.exit(0);// they are executed if variable ==  any of the above c's
					        
					  default:
					        //break;
				}
		}
		while (true);
	}
}
